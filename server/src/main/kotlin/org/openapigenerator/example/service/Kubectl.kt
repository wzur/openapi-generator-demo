package org.openapigenerator.example.service

import org.openapigenerator.example.Logging
import org.openapigenerator.example.logger
import kotlin.concurrent.thread

class Kubectl(
    private val runtime: Runtime = Runtime.getRuntime(),
    private val processBuilder: () -> ProcessBuilder = { ProcessBuilder() },
    private val kubectlCmd: String = "kubectl",
    private val namespace: String? = null,
    private val context: String? = null,
) {
    companion object : Logging {
        val logger = logger()
    }

    fun get(kind: String): Map<String, Any?>? {
        val command = listOf(
            "get",
            kind,
            "-o",
            "yaml"
        )
        val process = process(command)

        if (process.isAlive) {
            val output = process.inputStream.bufferedReader().readText()
            if (output.isNotEmpty()) {
                return createYaml().load(output)
            }
        } else {
            val error = process.errorStream.bufferedReader().readText()
            val exitCode = process.waitFor()
            logger.info("Process failed: ${exitCode}: ${error}")
        }
        return null
    }

    fun delete(kind: String) {
        val command = listOf(
            "delete",
            kind,
            "--wait=false"
        )
        val process = process(command, ProcessBuilder.Redirect.INHERIT, ProcessBuilder.Redirect.INHERIT)
        val exitCode = process.waitFor()
        logger.info("Process finished with ${exitCode}")
    }

    fun <T> apply(
        yaml: String,
        post: (exitCode: Int, error: String) -> T? = Post<T>()
    ): T? {
        val command = listOf(
            "apply",
            "-f",
            "-",
        )
        logger.info("Apply command: '${command.joinToString(" ") { it }}'")
        val process = process(command, ProcessBuilder.Redirect.INHERIT)
        if (process.isAlive) {
            logger.info("Process for apply ${process} is alive")
            process.outputStream.let {
                it.write(yaml.toByteArray())
                it.flush()
                it.close()
            }
        }
        val error = process.errorStream.bufferedReader().readText()
        val exitCode = process.waitFor()
        logger.info("Process finished with ${exitCode}")
        return post(exitCode, error)
    }

    private fun process(
        command: List<String>,
        redirectOutput: ProcessBuilder.Redirect? = null,
        redirectError: ProcessBuilder.Redirect? = null
    ): Process {
        val fullCommand = arrayListOf(kubectlCmd)::also {
            it.addAll(command)
        }
        if (namespace != null) fullCommand += listOf("-n", namespace)
        if (context != null) fullCommand += listOf("--context", context)

        logger.info("Creating a process for command: '${fullCommand.joinToString(" ") { it }}'")
        val pb = processBuilder()
            .command(fullCommand)
        if (redirectOutput != null) {
            pb.redirectOutput(redirectOutput)
        }
        if (redirectError != null) {
            pb.redirectError(redirectError)
        }
        val process = pb.start()
        runtime.addShutdownHook(
            thread(start = false) {
                if (process.isAlive) {
                    process.destroy()
                }
            }
        )
        return process
    }

    class Post<T> : (Int, String) -> T? {
        override operator fun invoke(exitCode: Int, error: String): T? {
            return null
        }
    }
}

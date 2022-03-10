package org.openapigenerator.example.cli

import picocli.CommandLine

class ExecutionExceptionHandler : CommandLine.IExecutionExceptionHandler {
    override fun handleExecutionException(
        exception: Exception,
        commandLine: CommandLine,
        parseResult: CommandLine.ParseResult
    ): Int {
        if (parseResult.hasMatchedOption("--stacktrace")) {
            throw exception
        }

        commandLine.err.println(commandLine.colorScheme.errorText("Error: ${exception.message}"))
        return if (commandLine.exitCodeExceptionMapper != null) {
            commandLine.exitCodeExceptionMapper.getExitCode(exception)
        } else {
            commandLine.commandSpec.exitCodeOnExecutionException()
        }
    }
}

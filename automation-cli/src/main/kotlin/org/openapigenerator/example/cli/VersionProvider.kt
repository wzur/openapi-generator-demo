package org.openapigenerator.example.cli

import picocli.CommandLine
import java.util.jar.Manifest

class VersionProvider(
    private val classLoader: ClassLoader = ClassLoader.getSystemClassLoader()
) : CommandLine.IVersionProvider {
    override fun getVersion(): Array<String> {
        val manifests = classLoader.getResources("META-INF/MANIFEST.MF")
            .asSequence()
            .map {
                it.openStream().use { input ->
                    Manifest(input).mainAttributes
                }
            }.filter {
                it.getValue("Implementation-Title")?.startsWith("automation-cli") == true
            }.toList()

        return manifests.asSequence()
            .map {
                arrayOf(
                    "Automation CLI",
                    "Version: ${it.getValue("Implementation-Version")}",
                    "Revision: ${it.getValue("Revision")}",
                    "Build-Date: ${it.getValue("Implementation-Build-Date")}",
                )
            }.first()
    }
}

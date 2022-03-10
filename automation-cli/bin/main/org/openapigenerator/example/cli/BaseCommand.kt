package org.openapigenerator.example.cli

import picocli.CommandLine

class Helper {
    @CommandLine.Option(
        names = arrayOf("--stacktrace"),
        description = arrayOf("Print out the stacktrace for all exceptions")
    )
    var stackTrace: Boolean = true

    @CommandLine.Option(
        names = ["-h", "--help"],
        usageHelp = true,
        description = ["display this help message"]
    )
    var usageHelpRequested = false
}

open class BaseCommand {
    @CommandLine.Mixin
    lateinit var helper: Helper

    @CommandLine.Option(
        names = arrayOf("--version", "-v"),
        description = arrayOf("display version info"),
        versionHelp = true
    )
    var versionInfoRequested: Boolean = true

    @CommandLine.Option(
        names = arrayOf("--output", "-o"),
        description = arrayOf(
            "format of the output",
            "Valid values: \${COMPLETION-CANDIDATES} (default: \${DEFAULT-VALUE})"
        ),
    )
    var format: OutputFormat = OutputFormat.text

    enum class OutputFormat {
        json, text
    }
}

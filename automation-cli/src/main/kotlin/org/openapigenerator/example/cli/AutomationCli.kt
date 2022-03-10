package org.openapigenerator.example.cli

import picocli.CommandLine
import picocli.CommandLine.Command
import kotlin.system.exitProcess

@Command(
    subcommands = [
        PodGroupCommand::class
    ],
    header = ["Automation command line interface"],
    name = "automation-cli",
    versionProvider = VersionProvider::class
)

class AutomationCli : BaseCommand()

fun main(vararg args: String) {
    exitProcess(
        CommandLine(AutomationCli())
            .setCaseInsensitiveEnumValuesAllowed(true)
            .setExecutionExceptionHandler(ExecutionExceptionHandler())
            .execute(*args)
    )
}

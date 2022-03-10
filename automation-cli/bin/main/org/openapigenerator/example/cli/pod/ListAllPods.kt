package org.openapigenerator.example.cli.pod

import picocli.CommandLine

@CommandLine.Command(
    name = "list",
    aliases = ["ls"],
    header = ["List available managed Kubernetes pods"]
)
class ListAllPods : Runnable, BasePodCommand() {
    override fun run() {
        println(client.getPods().format("Available managed Kubernetes pods"))
    }
}

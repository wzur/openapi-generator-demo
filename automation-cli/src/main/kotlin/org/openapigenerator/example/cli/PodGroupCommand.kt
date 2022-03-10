package org.openapigenerator.example.cli

import org.openapigenerator.example.cli.pod.CreatePod
import org.openapigenerator.example.cli.pod.DeletePod
import org.openapigenerator.example.cli.pod.ListAllPods
import org.openapigenerator.example.cli.pod.ShowPod
import picocli.CommandLine.Command

@Command(
    subcommands = [
        ListAllPods::class,
        CreatePod::class,
        DeletePod::class,
        ShowPod::class,
    ],
    name = "pods",
    header = ["Manage pods"]

)

class PodGroupCommand : BaseCommand()

package org.openapigenerator.example.cli.pod

import org.openapigenerator.example.client.infrastructure.ClientError
import org.openapigenerator.example.client.infrastructure.ClientException
import org.openapigenerator.example.client.models.AutomationError
import picocli.CommandLine

@CommandLine.Command(
    name = "show",
    header = ["Show information of an existing managed Kubernetes pod"]
)

class ShowPod : Runnable, BasePodCommand() {
    @CommandLine.Option(
        description = ["The name of a pod to delete"],
        required = true,
        names = ["--name", "-n"]
    )
    var name: String = ""

    override fun run() {
        try {
            client.getPod(name)
                .let { pod ->
                    println(pod.format())
                }
        } catch (ex: ClientException) {
            val clientError = ex.response as ClientError<*>
            getError<AutomationError>(clientError.body as String)?.msgs?.forEach {
                println(it)
            }
        }
    }

}

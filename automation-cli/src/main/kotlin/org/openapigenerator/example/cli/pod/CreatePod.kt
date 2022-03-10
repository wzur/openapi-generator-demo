package org.openapigenerator.example.cli.pod

import org.openapigenerator.example.client.infrastructure.ClientError
import org.openapigenerator.example.client.infrastructure.ClientException
import org.openapigenerator.example.client.models.AutomationError
import org.openapigenerator.example.client.models.Pod
import picocli.CommandLine

@CommandLine.Command(
    name = "create",
    aliases = ["cr"],
    header = ["Create new managed Kubernetes pod"]
)

class CreatePod : Runnable, BasePodCommand() {
    @CommandLine.Option(
        description = ["The name of newly created managed Kubernetes pod"],
        required = true,
        names = ["--name", "-n"]
    )
    var name: String = ""

    @CommandLine.Option(
        description = ["How long the pod should sleep (in seconds)"],
        names = ["--timeWait", "-t"]
    )
    var timeWait: Int? = null

    override fun run() {
        try {
            client.createPod(Pod(name, timeWait))
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

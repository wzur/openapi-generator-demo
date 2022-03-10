package org.openapigenerator.example.service

import org.openapigenerator.example.Logging
import org.openapigenerator.example.logger
import org.springframework.stereotype.Component
import java.text.MessageFormat
import java.util.*

@Component
class PodService(
    private val namespace: String = PodService::class.java.simpleName.lowercase(Locale.getDefault()),
    private val context: String = System.getProperty("kubernetes.cluster", "kind-openapidemo"),
) {
    companion object : Logging {
        val logger = logger()
    }

    init {
        javaClass.getResource("CreateNamespaceTemplate.yaml")
            ?.let { template ->
                kubectlFactory()
                    .apply(MessageFormat.format(template.readText(), namespace)) { exitCode, error ->
                        when (exitCode) {
                            0 -> logger.info("Namespace ${namespace} has been created")
                            else -> logger.info("Cannot create namespace ${namespace}: ${error}")
                        }
                    }
            }
            ?: logger.info("CreateNamespaceTemplate.yaml is not present, and no namespace has been created!")
    }

    fun createPod(name: String, waitTime: Int?): Pod? {
        logger.info("createPod for ${name}, ${waitTime}")
        if (getPod(name) == null) {
            javaClass.getResource("CreatePodTemplate.yaml")
                ?.let { template ->
                    return kubectlFactory(
                        namespace = namespace
                    ).apply(MessageFormat.format(template.readText(), name, waitTime)) { exitCode, error ->
                        when (exitCode) {
                            0 -> getPod(name)
                            else -> throw IllegalArgumentException(error)
                        }
                    }
                }
                ?: logger.info("CreatePodTemplate.yaml is not present, and no namespace has been created!")
        }
        return null
    }

    fun getPod(name: String): Pod? { // = pods[name]
        kubectlFactory(
            namespace = namespace
        ).get("pod/${name}")
            ?.let {
                return it.toPod()
            }
            ?: return null
    }

    fun getAllPods(): List<Pod> { // = pods.values.toList()
        kubectlFactory(
            namespace = namespace
        ).get("pods")
            ?.let {
                return (it.get("items") as List<*>).map { item ->
                    (item as Map<String, Any?>).toPod()
                }
            }
            ?: return listOf()
    }

    fun destroyPod(name: String): Pod? {
        kubectlFactory(
            namespace = namespace
        ).delete("pod/${name}")
        return getPod(name)
    }

    private fun kubectlFactory(namespace: String? = null): Kubectl = Kubectl(
        context = context,
        namespace = namespace,
    )

    private fun Map<String, Any?>.toPod(): Pod {
        val podMetadata = this["metadata"] as Map<*, *>
        val deletionTimestamp = podMetadata.get("deletionTimestamp") as? String
        val podAnnotations = podMetadata.get("annotations") as? Map<*, *>
        val podStatus = this["status"] as? Map<*, *>
        return Pod(
            name = podMetadata.get("name") as String,
            waitTime = (podAnnotations?.get("waitTime") as? String)?.toInt(),
            status = when (deletionTimestamp?.isNotEmpty()) {
                true -> "Terminating"
                else -> podStatus?.get("phase") as? String ?: "Unknown"
            }
        )
    }
}

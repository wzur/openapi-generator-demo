package org.openapigenerator.example.cli.pod

import org.openapigenerator.example.cli.BaseCommand
import org.openapigenerator.example.client.apis.PodsApi
import org.openapigenerator.example.client.infrastructure.Serializer
import org.openapigenerator.example.client.models.Pod
import com.squareup.moshi.adapter

open class BasePodCommand(
    internal val client: PodsApi = PodsApi()
) : BaseCommand() {
    internal fun Pod.format(): String = when (format) {
        OutputFormat.text -> "name: ${name}, waitTime: ${waitTime}, status: ${status}"
        OutputFormat.json -> Serializer.moshi.adapter(javaClass).toJson(this)
    }

    internal fun List<Pod>.format(header: String): String = when (format) {
        OutputFormat.text -> {
            val sb = StringBuilder()
                .append(header)
            this.forEach { pod -> sb.append(System.lineSeparator()).append(pod.format()) }
            sb.toString()
        }
        OutputFormat.json -> {
            StringBuilder()
                .append("{\"name\":")
                .append(Serializer.moshi.adapter(header.javaClass).toJson(header))
                .append(",\"items\":[")
                .append(this.map { it.format() }.joinToString(","))
                .append("]}")
                .toString()
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    protected inline fun <reified T> getError(body: String?): T? {
        if (body == null)
            return null
        return Serializer.moshi.adapter<T>().fromJson(body)
    }

}

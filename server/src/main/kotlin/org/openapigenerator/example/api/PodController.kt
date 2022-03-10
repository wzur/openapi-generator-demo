package org.openapigenerator.example.api

import org.openapigenerator.example.apis.PodsApi
import org.openapigenerator.example.models.Pod
import org.openapigenerator.example.service.PodService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.openapigenerator.example.service.Pod as PodModel

@RestController
class PodController(private val service: PodService) : PodsApi {
    override fun createPod(pod: Pod): ResponseEntity<Pod> {
        val (name, waitTime) = pod
        return service.createPod(name, waitTime)?.toApiPod()
            ?.let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
            ?: throw IllegalArgumentException("Pod with id ${name} already exists")
    }

    override fun getPod(name: String): ResponseEntity<Pod> {
        return service.getPod(name)?.toApiPod()
            ?.let { ResponseEntity.ok(it) }
            ?: throw IllegalArgumentException("Pod with id ${name} doesn't exist")
    }

    override fun getPods(): ResponseEntity<List<Pod>> {
        return service.getAllPods().map { it.toApiPod() }
            .let { ResponseEntity.ok(it) }
    }

    override fun destroyPod(name: String): ResponseEntity<Pod> {
        return service.destroyPod(name)?.toApiPod()
            ?.let { ResponseEntity.ok(it) }
            ?: throw IllegalArgumentException("Pod with id ${name} doesn't exist")
    }

    private fun PodModel.toApiPod(): Pod = Pod(
        name = name,
        waitTime = waitTime,
        status = status
    )
}

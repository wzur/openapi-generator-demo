/**
 * Automation Server
 *
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapigenerator.example.client.apis

import java.io.IOException

import org.openapigenerator.example.client.models.AutomationError
import org.openapigenerator.example.client.models.Pod

import com.squareup.moshi.Json

import org.openapigenerator.example.client.infrastructure.ApiClient
import org.openapigenerator.example.client.infrastructure.ApiResponse
import org.openapigenerator.example.client.infrastructure.ClientException
import org.openapigenerator.example.client.infrastructure.ClientError
import org.openapigenerator.example.client.infrastructure.ServerException
import org.openapigenerator.example.client.infrastructure.ServerError
import org.openapigenerator.example.client.infrastructure.MultiValueMap
import org.openapigenerator.example.client.infrastructure.RequestConfig
import org.openapigenerator.example.client.infrastructure.RequestMethod
import org.openapigenerator.example.client.infrastructure.ResponseType
import org.openapigenerator.example.client.infrastructure.Success
import org.openapigenerator.example.client.infrastructure.toMultiValue

class PodsApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost:8080/v1")
        }
    }

    /**
    * Create a new managed Kubernetes pod
    * Creates and returns information about newly created managed Kubernetes pod
    * @param pod request to create a new managed Kubernetes pod 
    * @return Pod
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun createPod(pod: Pod) : Pod {
        val localVarResponse = createPodWithHttpInfo(pod = pod)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Pod
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * Create a new managed Kubernetes pod
    * Creates and returns information about newly created managed Kubernetes pod
    * @param pod request to create a new managed Kubernetes pod 
    * @return ApiResponse<Pod?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun createPodWithHttpInfo(pod: Pod) : ApiResponse<Pod?> {
        val localVariableConfig = createPodRequestConfig(pod = pod)

        return request<Pod, Pod>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation createPod
    *
    * @param pod request to create a new managed Kubernetes pod 
    * @return RequestConfig
    */
    fun createPodRequestConfig(pod: Pod) : RequestConfig<Pod> {
        val localVariableBody = pod
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/pods",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * Destroy managed Kubernetes pod
    * Destroy a managed Kubernetes pod
    * @param name  
    * @return Pod
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun destroyPod(name: kotlin.String) : Pod {
        val localVarResponse = destroyPodWithHttpInfo(name = name)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Pod
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * Destroy managed Kubernetes pod
    * Destroy a managed Kubernetes pod
    * @param name  
    * @return ApiResponse<Pod?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun destroyPodWithHttpInfo(name: kotlin.String) : ApiResponse<Pod?> {
        val localVariableConfig = destroyPodRequestConfig(name = name)

        return request<Unit, Pod>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation destroyPod
    *
    * @param name  
    * @return RequestConfig
    */
    fun destroyPodRequestConfig(name: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.DELETE,
            path = "/pods/{name}".replace("{"+"name"+"}", "$name"),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * Managed Kubernetes pod
    * Get a managed Kubernetes pod
    * @param name  
    * @return Pod
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getPod(name: kotlin.String) : Pod {
        val localVarResponse = getPodWithHttpInfo(name = name)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Pod
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * Managed Kubernetes pod
    * Get a managed Kubernetes pod
    * @param name  
    * @return ApiResponse<Pod?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getPodWithHttpInfo(name: kotlin.String) : ApiResponse<Pod?> {
        val localVariableConfig = getPodRequestConfig(name = name)

        return request<Unit, Pod>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation getPod
    *
    * @param name  
    * @return RequestConfig
    */
    fun getPodRequestConfig(name: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/pods/{name}".replace("{"+"name"+"}", "$name"),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * List all managed Kubernetes pods
    * Returns the list of managed Kubernetes pods
    * @return kotlin.collections.List<Pod>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getPods() : kotlin.collections.List<Pod> {
        val localVarResponse = getPodsWithHttpInfo()

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as kotlin.collections.List<Pod>
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
    * List all managed Kubernetes pods
    * Returns the list of managed Kubernetes pods
    * @return ApiResponse<kotlin.collections.List<Pod>?>
    * @throws IllegalStateException If the request is not correctly configured
    * @throws IOException Rethrows the OkHttp execute method exception
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getPodsWithHttpInfo() : ApiResponse<kotlin.collections.List<Pod>?> {
        val localVariableConfig = getPodsRequestConfig()

        return request<Unit, kotlin.collections.List<Pod>>(
            localVariableConfig
        )
    }

    /**
    * To obtain the request config of the operation getPods
    *
    * @return RequestConfig
    */
    fun getPodsRequestConfig() : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/pods",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}
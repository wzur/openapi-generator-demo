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

package org.openapigenerator.example.client.models


import com.squareup.moshi.Json

/**
 * 
 *
 * @param msgs 
 * @param errorCode 
 */

data class AutomationError (

    @Json(name = "msgs")
    val msgs: kotlin.collections.List<kotlin.String>,

    @Json(name = "errorCode")
    val errorCode: kotlin.Int

)


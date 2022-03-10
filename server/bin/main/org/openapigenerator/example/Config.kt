package org.openapigenerator.example

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "app.config")
data class Config(
    val namespace: String,
    val context: String,
)

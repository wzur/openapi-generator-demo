package org.openapigenerator.example.service

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml

fun createYaml(): Yaml {
    val options = DumperOptions()
    options.isPrettyFlow = true
    options.defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
    return Yaml(options)
}

fun Map<String, Any?>.toJson(): String {
    val options = DumperOptions()
    options.isPrettyFlow = true
    options.defaultFlowStyle = DumperOptions.FlowStyle.FLOW
    options.defaultScalarStyle = DumperOptions.ScalarStyle.DOUBLE_QUOTED

    return Yaml(options).dump(this)
}

package pl.net.gwynder.shopping.central.entities.configuration

import com.fasterxml.jackson.annotation.JsonCreator

data class ProxyApplicationData @JsonCreator constructor(
        val active: Boolean,
        val path: String,
        val displayName: String,
        val startPath: String
)
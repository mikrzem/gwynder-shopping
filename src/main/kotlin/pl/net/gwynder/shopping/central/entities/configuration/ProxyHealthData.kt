package pl.net.gwynder.shopping.central.entities.configuration

import com.fasterxml.jackson.annotation.JsonCreator

data class ProxyHealthData @JsonCreator constructor(
        val active: Boolean,
        val path: String?
)
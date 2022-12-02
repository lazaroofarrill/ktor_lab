package com.lazaroofarrill.framework

import io.ktor.server.routing.*

data class Module(
    val controllers: List<Controller> = emptyList(),
    val prefix: String? = null
)

typealias Controller = Route.() -> Route



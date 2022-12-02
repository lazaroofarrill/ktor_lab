package com.lazaroofarrill.framework.plugins

import com.lazaroofarrill.framework.Module
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(modules: List<Module>) {
    routing {
        modules.forEach { module ->
            module.controllers.forEach { controller ->
                controller()
            }
        }
    }
}

package com.lazaroofarrill.ktor_lab.framework.plugins

import com.lazaroofarrill.ktor_lab.framework.Module
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(modules: List<Module>) {
    routing {
        get {
            call.respondRedirect("/articles")
        }
        modules.forEach { module ->
            route(module.prefix ?: "") {
                module.controllers.forEach { controller ->
                    controller.controller(this)
                }
            }
        }

        static("/static") {
            resources("files")
        }
    }
}

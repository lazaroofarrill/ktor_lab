package com.lazaroofarrill

import com.lazaroofarrill.framework.plugins.configureRouting
import com.lazaroofarrill.framework.plugins.configureSerialization
import com.lazaroofarrill.framework.plugins.configureTemplating
import com.lazaroofarrill.modules.articles.articlesModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
        watchPaths = listOf("classes")
    )
        .start(wait = true)
}

val appModules = listOf(articlesModule)

fun Application.module() {
    configureSerialization()
    configureTemplating()
    configureRouting(appModules)
}

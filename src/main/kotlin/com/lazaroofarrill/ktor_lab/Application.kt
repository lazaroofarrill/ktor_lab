package com.lazaroofarrill.ktor_lab

import com.lazaroofarrill.ktor_lab.database.DatabaseFactory
import com.lazaroofarrill.ktor_lab.framework.plugins.configureRouting
import com.lazaroofarrill.ktor_lab.framework.plugins.configureTemplating
import com.lazaroofarrill.ktor_lab.modules.articles.articlesModule
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val appModules = listOf(articlesModule)

fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureTemplating()
    configureRouting(appModules)
}

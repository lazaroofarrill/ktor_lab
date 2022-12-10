package com.lazaroofarrill.ktor_lab.framework.plugins

import com.lazaroofarrill.ktor_lab.database.ArticleCache
import com.lazaroofarrill.ktor_lab.framework.Module
import com.lazaroofarrill.ktor_lab.modules.articles.entities.ArticleDAO
import com.lazaroofarrill.ktor_lab.modules.articles.entities.IArticleDAO
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import java.io.File

fun Application.configureRouting(modules: List<Module>) {
    println("Around you")
    println(environment.config.property("storage.ehcacheFilePath").getString())

    val dao: IArticleDAO = ArticleCache(
        ArticleDAO(),
        File(environment.config.property("storage.ehcacheFilePath").getString())
    ).apply {
        runBlocking {
            if (allArticles().isEmpty()) {
                addNewArticle(
                    "The drive to develop!",
                    "...it's what keeps me going"
                )
            }
        }
    }

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

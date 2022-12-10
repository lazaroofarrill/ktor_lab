package com.lazaroofarrill.ktor_lab.modules.articles.controllers

import com.lazaroofarrill.ktor_lab.framework.interfaces.IController
import com.lazaroofarrill.ktor_lab.modules.articles.entities.Article
import com.lazaroofarrill.ktor_lab.modules.articles.services.ArticleService
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

data class ArticleController(val articleService: ArticleService) : IController {
    override val controller = fun Route.(): Route {

        route("articles") {
            get {
                call.respond(
                    FreeMarkerContent(
                        "index.ftl",
                        mapOf("articles" to articleService.getArticles())
                    )
                )
            }
            get("new") {
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }
            post {
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")

                val newEntry = articleService.insertArticle(
                    Article.newEntry(
                        title, body
                    )
                )

                call.respondRedirect("/articles/${newEntry?.id}")
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(
                    FreeMarkerContent(
                        "show.ftl",
                        mapOf(
                            "article" to (articleService.getArticle(id)
                                ?: throw Error("Article not found"))
                        )
                    )
                )
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(
                    FreeMarkerContent(
                        "edit.ftl",
                        mapOf(
                            "article" to
                                    (articleService.getArticle(id)
                                        ?: throw Error("Article not found"))
                        )
                    )
                )
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        articleService.updateArticle(id, title, body)
                        call.respondRedirect("/articles/$id")
                    }

                    "delete" -> {
                        articleService.removeArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }

        return this
    }
}

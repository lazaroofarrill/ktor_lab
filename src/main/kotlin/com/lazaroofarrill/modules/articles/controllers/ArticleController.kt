package com.lazaroofarrill.modules.articles.controllers

import com.lazaroofarrill.framework.Controller
import com.lazaroofarrill.modules.articles.entities.Article
import com.lazaroofarrill.modules.articles.services.ArticleService
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

val articleController: Controller = {

    route("articles") {
        get {
            call.respond(
                FreeMarkerContent(
                    "index.ftl",
                    mapOf("articles" to ArticleService.getArticles())
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

            val newEntry = ArticleService.insertArticle(
                Article.newEntry(
                    title, body
                )
            )
            call.respondRedirect("/articles/${newEntry.id}")
        }
        get("{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respond(
                FreeMarkerContent(
                    "show.ftl",
                    mapOf(
                        "article" to (ArticleService.getArticle(id)
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
                                (ArticleService.getArticle(id)
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
                    ArticleService.updateArticle(id, title, body)
                    call.respondRedirect("/articles/$id")
                }

                "delete" -> {
                    ArticleService.removeArticle(id)
                    call.respondRedirect("/articles")
                }
            }
        }
    }
}

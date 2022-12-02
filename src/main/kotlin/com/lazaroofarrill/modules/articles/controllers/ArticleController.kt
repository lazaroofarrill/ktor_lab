package com.lazaroofarrill.modules.articles.controllers

import com.lazaroofarrill.framework.Controller
import com.lazaroofarrill.modules.articles.services.ArticleService
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
            // Show a page with fields for creating a new article
            call.respondText("hello")
        }
        post {
            // Save an article
        }
        get("{id}") {
            // Show an article with a specific id
        }
        get("{id}/edit") {
            // Show a page with fields for editing an article
        }
        post("{id}") {
            // Update or delete an article
        }
    }
}

package com.lazaroofarrill.ktor_lab.modules.articles

import com.lazaroofarrill.ktor_lab.framework.Module
import com.lazaroofarrill.ktor_lab.modules.articles.controllers.ArticleController
import com.lazaroofarrill.ktor_lab.modules.articles.entities.ArticleDAO
import com.lazaroofarrill.ktor_lab.modules.articles.services.ArticleService

val articlesModule = Module(
    controllers = listOf(
        ArticleController(
            ArticleService(
                ArticleDAO()
            )
        )
    )
)



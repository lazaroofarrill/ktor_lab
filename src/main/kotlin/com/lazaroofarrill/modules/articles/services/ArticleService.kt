package com.lazaroofarrill.modules.articles.services

import com.lazaroofarrill.modules.articles.entities.Article
import com.lazaroofarrill.modules.articles.entities.ArticleRepository


object ArticleService {
    fun getArticles(): List<Article> {
        return ArticleRepository.articles
    }
}

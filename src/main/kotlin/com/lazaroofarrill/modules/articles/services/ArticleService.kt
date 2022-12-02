package com.lazaroofarrill.modules.articles.services

import com.lazaroofarrill.modules.articles.entities.Article
import com.lazaroofarrill.modules.articles.entities.ArticleRepository


object ArticleService {
    fun getArticles(): List<Article> {
        return ArticleRepository.articles
    }

    fun insertArticle(newArticle: Article): Article {
        return ArticleRepository.addArticle(newArticle)
    }

    fun getArticle(id: Int): Article? {
        return ArticleRepository.getArticle(id)
    }

    fun updateArticle(id: Int, title: String? = null, body: String? = null): Boolean {
        return ArticleRepository.updateArticle(id, title, body)
    }

    fun removeArticle(id: Int): Boolean {
        return ArticleRepository.removeArticle(id)
    }
}

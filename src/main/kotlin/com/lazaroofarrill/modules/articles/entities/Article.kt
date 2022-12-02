package com.lazaroofarrill.modules.articles.entities

import java.util.concurrent.atomic.AtomicInteger

class Article
private constructor(val id: Int, var title: String, var body: String) {
    companion object {
        private val idCounter = AtomicInteger()

        fun newEntry(title: String, body: String) =
            Article(idCounter.getAndIncrement(), title, body)
    }
}

private val articleStorage = mutableListOf(
    Article.newEntry(
        "The drive to develop!",
        "...it's what keeps me going."
    )
)

object ArticleRepository {
    val articles get() = articleStorage.toList()

    fun addArticle(article: Article): Article {
        articleStorage.add(article)

        return articleStorage.last()
    }

    fun getArticle(id: Int): Article? {
        return this.articles.find { it.id == id }
    }

    fun updateArticle(id: Int, title: String?, body: String?): Boolean {
        val idx = articleStorage.indexOf(getArticle(id) ?: return false)
        title?.let {
            articleStorage[idx].title = it
        }
        body?.let {
            articleStorage[idx].body = it
        }

        return true
    }

    fun removeArticle(id: Int): Boolean {
        return articleStorage.remove(getArticle(id))
    }
}

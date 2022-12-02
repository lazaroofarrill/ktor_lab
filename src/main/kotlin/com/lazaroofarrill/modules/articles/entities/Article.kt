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

private val articlesStorage = mutableListOf(
    Article.newEntry(
        "The drive to develop!",
        "...it's what keeps me going."
    )
)

object ArticleRepository {
    val articles get() = articlesStorage.toList()

    fun addArticle(article: Article) {
        articlesStorage.add(article)
    }
}

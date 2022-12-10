package com.lazaroofarrill.ktor_lab.modules.articles.entities

import org.jetbrains.exposed.sql.Table
import java.io.Serializable
import java.util.concurrent.atomic.AtomicInteger

class Article(val id: Int, var title: String, var body: String): Serializable {
    companion object {
        private val idCounter = AtomicInteger()

        fun newEntry(title: String, body: String) =
            Article(idCounter.getAndIncrement(), title, body)
    }
}

object Articles : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)

    override val primaryKey = PrimaryKey(id)
}

private val articleStorage = mutableListOf(
    Article.newEntry(
        "The drive to develop!",
        "...it's what keeps me going."
    )
)

interface IArticleDAO {

    suspend fun allArticles(): List<Article>
    suspend fun article(id: Int): Article?
    suspend fun addNewArticle(title: String, body: String): Article?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean
}

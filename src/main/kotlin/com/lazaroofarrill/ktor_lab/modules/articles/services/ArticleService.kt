package com.lazaroofarrill.ktor_lab.modules.articles.services

import com.lazaroofarrill.ktor_lab.framework.interfaces.ISeed
import com.lazaroofarrill.ktor_lab.modules.articles.entities.Article
import com.lazaroofarrill.ktor_lab.modules.articles.entities.IArticleDAO
import kotlinx.coroutines.runBlocking


data class ArticleService(val articleDao: IArticleDAO) : ISeed {
    suspend fun getArticles(): List<Article> {
        return articleDao.allArticles()
    }

    suspend fun insertArticle(newArticle: Article): Article? {
        return articleDao.addNewArticle(newArticle.title, newArticle.body)
    }

    suspend fun getArticle(id: Int): Article? {
        return articleDao.article(id)
    }

    suspend fun updateArticle(id: Int, title: String?, body: String?): Boolean {
        return articleDao.editArticle(id, title, body)
    }

    suspend fun removeArticle(id: Int): Boolean {
        return articleDao.deleteArticle(id)
    }

    override fun seed() {
        runBlocking {
            insertArticle(
                Article.newEntry(
                    "the way of kings",
                    "the sound of Roshar is true and mighty"
                )
            )
        }
    }
}

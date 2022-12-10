package com.lazaroofarrill.ktor_lab.database

import com.lazaroofarrill.ktor_lab.modules.articles.entities.Articles
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        println(config)
        val driverClassName =
            config.property("storage.driverClassName").getString()
        val jdbcURL = config.property("storage.jdbcUrl").getString() +
                (config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
                    "./$it"
                } ?: "")

        val database = Database.connect(
            createHikariDataSource(
                url = jdbcURL,
                driver = driverClassName
            )
        )

        transaction(database) {
            SchemaUtils.create(Articles)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    private fun createHikariDataSource(
        url: String,
        driver: String
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })
}

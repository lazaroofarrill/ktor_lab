package com.lazaroofarrill

import com.lazaroofarrill.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
        client.get("/lol").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("lol World!", bodyAsText())
        }
    }
}
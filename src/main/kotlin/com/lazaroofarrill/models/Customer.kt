package com.lazaroofarrill.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Customer(
    val id: String? = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val email: String
)

val customerStorage = mutableListOf<Customer>()
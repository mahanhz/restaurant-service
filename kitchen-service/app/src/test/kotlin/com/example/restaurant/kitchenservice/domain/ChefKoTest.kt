package com.example.restaurant.kitchenservice.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class ChefKoTest : StringSpec({
    "should create a chef" {
        io.kotest.data.forAll(
            table(
                headers("id", "name", "orderId"),
                row(1, "Jo", 4),
                row(200, "b".repeat(200), 100)
            )
        ) { id: Long, name: String, orderId: Long ->
            Chef(
                id = Id(id),
                name = Name(name),
                orders = setOf(Id(orderId))
            ) shouldNotBe null
        }
    }

    "should not create a chef" {
        io.kotest.data.forAll(
            table(
                headers("id", "name", "orderId", "message"),
                row(1, "", 4, "Name cannot be blank"),
                row(2, "a", 1, "Name must be at least 2 characters and no more than 200"),
                row(100, "b".repeat(201), 200, "Name must be at least 2 characters and no more than 200")
            )
        ) { id: Long, name: String, orderId: Long, message: String ->
            val exception = shouldThrow<IllegalArgumentException> {
                Chef(id = Id(id), name = Name(name), orders = setOf(Id(orderId)))
            }
            exception.message shouldBe message
        }
    }
})

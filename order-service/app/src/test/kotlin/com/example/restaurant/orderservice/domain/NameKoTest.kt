package com.example.restaurant.orderservice.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class NameKoTest : StringSpec({
    "should be valid names" {
        io.kotest.data.forAll(
            table(
                headers("name"),
                row("Jo"),
                row("b".repeat(200)),
            )
        ) { name: String ->
            Name(name) shouldNotBe null
        }
    }

    "should be invalid names" {
        io.kotest.data.forAll(
            table(
                headers("name", "message"),
                row("", "Name cannot be blank"),
                row("a", "Name must be at least 2 characters and no more than 200"),
                row("b".repeat(201), "Name must be at least 2 characters and no more than 200"),
            )
        ) { name: String, message: String ->
            val exception = shouldThrow<IllegalArgumentException> {
                Name(name)
            }
            exception.message shouldBe message
        }
    }
})
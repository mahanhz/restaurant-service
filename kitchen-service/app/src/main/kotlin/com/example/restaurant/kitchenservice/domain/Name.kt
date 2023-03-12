package com.example.restaurant.kitchenservice.domain

@JvmInline
value class Name (
    val value: String
) {
    init {
        require(value.isNotBlank()) { "Name cannot be blank" }
        require(value.length in 2..200) { "Name must be at least 2 characters and no more than 200" }
    }
}
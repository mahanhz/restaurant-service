package com.example.restaurant.kitchenservice.application.api

data class OrderDetailsApi(
    var id: Long? = null,
    val name: String,
    val quantity: Int
)

package com.example.restaurant.kitchenservice.application.api

data class DetailedChefApi(
    var id: Long? = null,
    val chef: String? = null,
    val orders: Set<OrderDetailsApi>? = HashSet()
)

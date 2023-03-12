package com.example.restaurant.kitchenservice.application.api

data class ChefApi (
    var id: Long? = null,
    val chef: String? = null,
    val orders: Set<Long>? = HashSet(),
    val errors: Set<String>? = HashSet(),
)
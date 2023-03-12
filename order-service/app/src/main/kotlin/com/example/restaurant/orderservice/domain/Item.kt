package com.example.restaurant.orderservice.domain

data class Item (val name: Name, val quantity: Int)  {
    init {
        require(quantity > 0) { "The must be at least one of item '${name.value}'" }
    }
}
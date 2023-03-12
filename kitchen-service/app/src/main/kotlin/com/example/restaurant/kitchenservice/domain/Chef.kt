package com.example.restaurant.kitchenservice.domain

data class Chef (
    val id: Id,
    val name: Name,
    val orders: Set<Id>
) {
    fun isAvailable(): Boolean {
        return orders.size < 3
    }
}

@JvmInline
value class Id (
    val value: Long
)


package com.example.restaurant.orderservice.domain

import java.time.LocalDate

data class Order (
    val id: OrderId,
    val date: LocalDate,
    val state: State,
    val items: Set<Item>
)

@JvmInline
value class OrderId (
    val value: Long
)

enum class State {
    CREATED, ASSIGNED, REJECTED, COMPLETED
}

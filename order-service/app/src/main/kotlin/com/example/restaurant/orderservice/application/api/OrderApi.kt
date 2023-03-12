package com.example.restaurant.orderservice.application.api

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class OrderApi (
    var id: Long? = null,
    var state: String? = null,
    @JsonFormat(pattern="yyyy-MM-dd") val date: LocalDate,
    val items: Set<ItemApi>,
)

data class ItemApi (
    val name: String,
    val quantity: Int
)
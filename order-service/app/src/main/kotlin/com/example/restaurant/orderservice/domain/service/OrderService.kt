package com.example.restaurant.orderservice.domain.service

import com.example.restaurant.orderservice.domain.Item
import com.example.restaurant.orderservice.domain.Order
import com.example.restaurant.orderservice.domain.OrderId
import java.time.LocalDate

interface OrderService {

    fun createOrder(date: LocalDate, items: Set<Item>): Order
    fun order(id: OrderId): Order
}

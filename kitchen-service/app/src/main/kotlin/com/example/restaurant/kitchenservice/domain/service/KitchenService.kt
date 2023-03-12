package com.example.restaurant.kitchenservice.domain.service

import com.example.restaurant.kitchenservice.domain.Chef
import com.example.restaurant.kitchenservice.domain.Id
import com.example.restaurant.kitchenservice.domain.Name

interface KitchenService {

    fun createChef(name: Name): Chef
    fun assignChef(orderId: Long): Chef

    fun chefs(): Set<Chef>
    fun chef(id: Id): Chef
}
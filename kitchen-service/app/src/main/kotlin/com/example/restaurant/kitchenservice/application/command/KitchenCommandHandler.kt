package com.example.restaurant.kitchenservice.application.command

import com.example.restaurant.kitchenservice.application.api.ChefApi
import com.example.restaurant.kitchenservice.application.api.DetailedChefApi
import com.example.restaurant.kitchenservice.application.api.OrderDetailsApi
import com.example.restaurant.kitchenservice.application.exception.KitchenServiceException
import com.example.restaurant.kitchenservice.domain.Id
import com.example.restaurant.kitchenservice.domain.Name
import com.example.restaurant.kitchenservice.domain.service.KitchenService
import com.example.restaurant.orderservice.application.command.OrderCommandHandler
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class KitchenCommandHandler(
    private val kitchenService: KitchenService,
    private val orderCommandHandler: OrderCommandHandler,
) {
    val logger: Logger = LoggerFactory.getLogger(KitchenCommandHandler::class.java)

    //val order: Order? = null

    @Transactional
    fun createChef(chefName: String): ChefApi {
        val chef = kitchenService.createChef(name = Name(chefName))

        return ChefApi(
            id = chef.id.value,
            chef = chef.name.value,
            orders = chef.orders.map { it.value }.toSet()
        )
    }

    fun chef(id: Long): DetailedChefApi {
        val chef = kitchenService.chef(id = Id(value = id))

        val orderDetailsApi = chef.orders.map {
            orderCommandHandler.order(it.value).items.map { item ->
                OrderDetailsApi(
                    id = it.value,
                    name = item.name,
                    quantity = item.quantity
                )
            }
        }.flatten().toSet()

        return DetailedChefApi(
            id = chef.id.value,
            chef = chef.name.value,
            orders = orderDetailsApi
        )
    }

    @Transactional
    fun assignChef(orderId: Long): ChefApi {
        return try {
            val chef = kitchenService.assignChef(orderId)
            ChefApi(
                id = chef.id.value,
                chef = chef.name.value,
                orders = chef.orders.map { it.value }.toSet()
            )
        } catch (kse: KitchenServiceException) {
            ChefApi(
                orders = setOf(orderId),
                errors = setOf("${kse.code} - ${kse.message}")
            )
        }
    }
}
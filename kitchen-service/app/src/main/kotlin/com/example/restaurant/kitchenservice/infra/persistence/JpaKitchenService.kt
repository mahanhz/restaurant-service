package com.example.restaurant.kitchenservice.infra.persistence

import com.example.restaurant.kitchenservice.application.exception.ErrorCode
import com.example.restaurant.kitchenservice.application.exception.KitchenServiceException
import com.example.restaurant.kitchenservice.domain.Chef
import com.example.restaurant.kitchenservice.domain.Id
import com.example.restaurant.kitchenservice.domain.Name
import com.example.restaurant.kitchenservice.domain.service.KitchenService
import com.example.restaurant.orderservice.application.command.OrderCommandHandler
import org.springframework.stereotype.Service

@Service
internal class JpaKitchenService(
    private val chefRepository: ChefRepository,
    private val orderCommandHandler: OrderCommandHandler
): KitchenService {
    override fun createChef(name: Name): Chef {
        val chefEntity = ChefEntity(name = name.value)
        val chef = chefRepository.save(chefEntity)

        return Chef(id = Id(chef.id!!), name = Name(chef.name!!), orders = chef.orders!!.map { Id(it.id!!) }.toSet())
    }

    override fun assignChef(orderId: Long): Chef {
        val chef = this.chefs().firstOrNull { it.isAvailable() }
        if (chef != null) {
            val chefOrders = mutableSetOf(OrderIdEntity(orderId))
            chefOrders.addAll(chef.orders.map { OrderIdEntity(it.value) }.toSet())
            val chefEntity = ChefEntity(id = chef.id.value, name = chef.name.value, orders = chefOrders)
            chefRepository.save(chefEntity)
            return chef
        } else {
            throw KitchenServiceException(
                "There are no available chefs to prepare order ${orderId}", ErrorCode.ERR_KIT_SER_2
            )
        }
    }

    override fun chefs(): Set<Chef> {
        val chefs = chefRepository.findAll()

        return chefs.map { it ->
            Chef(id = Id(it.id!!), name = Name(it.name!!), orders = it.orders!!.map { Id(it.id!!) }.toSet())
        }.toSet()
    }

    override fun chef(id: Id): Chef {
        val chef = chefRepository.findById(id.value).orElseThrow {
            KitchenServiceException("Could not find chef with id ${id.value}", ErrorCode.ERR_KIT_SER_1)
        }

        return Chef(id = Id(chef.id!!), name = Name(chef.name!!), orders = chef.orders!!.map { Id(it.id!!) }.toSet())
    }
}
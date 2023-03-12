package com.example.restaurant.orderservice.infra.persistence

import com.example.restaurant.orderservice.application.exception.ErrorCode
import com.example.restaurant.orderservice.application.exception.OrderServiceException
import com.example.restaurant.orderservice.domain.*
import com.example.restaurant.orderservice.domain.service.OrderService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.stream.Collectors

@Service
internal class JpaOrderService(
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
): OrderService {

    @Transactional
    override fun createOrder(date: LocalDate, items: Set<Item>): Order {
        val itemEntities = items.stream().map {
            ItemEntity(name = it.name.value, quantity = it.quantity)
        }.collect(Collectors.toSet())
        itemRepository.saveAll(itemEntities)

        val orderEntity = OrderEntity(date = date, items = itemEntities, state = State.CREATED)
        val order = orderRepository.save(orderEntity)

        return Order(
            id = OrderId(value = order.id!!),
            date = order.date!!,
            items = order.items!!.stream().map {
                Item(name = Name(it.name!!), quantity = it.quantity!!)
            }.collect(Collectors.toSet()),
            state = order.state!!,
        )
    }

    override fun order(id: OrderId): Order {
        val order = orderRepository.findById(id.value).orElseThrow {
            OrderServiceException("Could not find order with id ${id.value}", ErrorCode.ERR_ORD_SER_1)
        }

        return Order(
            id = OrderId(value = order.id!!),
            date = order.date!!,
            items = order.items!!.stream().map {
                Item(name = Name(it.name!!), quantity = it.quantity!!)
            }.collect(Collectors.toSet()),
            state = order.state!!
        )
    }
}
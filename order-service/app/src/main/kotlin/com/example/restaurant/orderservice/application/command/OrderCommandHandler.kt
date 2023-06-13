package com.example.restaurant.orderservice.application.command

import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import com.example.restaurant.orderservice.domain.Item
import com.example.restaurant.orderservice.domain.Name
import com.example.restaurant.orderservice.domain.OrderId
import com.example.restaurant.orderservice.domain.event.OrderCreatedEvent
import com.example.restaurant.orderservice.domain.service.OrderService
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class OrderCommandHandler(
    private val orderService: OrderService,
    private val eventPublisher: ApplicationEventPublisher
) {

    val logger: Logger = LoggerFactory.getLogger(OrderCommandHandler::class.java)

    @Transactional
    fun createOrder(order: OrderApi): OrderApi {
        val items = order.items.stream().map {
            Item(name = Name(it.name), quantity = it.quantity)
        }.collect(Collectors.toSet())
        val createdOrder = orderService.createOrder(date = order.date, items = items)

        eventPublisher.publishEvent(OrderCreatedEvent.of(createdOrder))

        return OrderApi(
            id = createdOrder.id.value,
            date = createdOrder.date,
            items = order.items,
            state = createdOrder.state.toString()
        )
    }

    fun order(id: Long): OrderApi {
        val order = orderService.order(id = OrderId(value = id))

        return OrderApi(
            id = order.id.value,
            date = order.date,
            items = order.items.stream().map {
                ItemApi(name = it.name.value, quantity = it.quantity)
            }.collect(Collectors.toSet()),
            state = order.state.toString()
        )
    }
}

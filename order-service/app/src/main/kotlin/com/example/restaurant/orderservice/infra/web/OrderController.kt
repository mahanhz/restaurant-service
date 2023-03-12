package com.example.restaurant.orderservice.infra.web

import com.example.restaurant.orderservice.application.command.OrderCommandHandler
import com.example.restaurant.orderservice.application.api.OrderApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
internal class OrderController(private val orderCommandHandler: OrderCommandHandler) {

    val logger: Logger = LoggerFactory.getLogger(OrderController::class.java)

    @PostMapping("/orders")
    fun createOrder(@RequestBody order: OrderApi): ResponseEntity<OrderApi> {
        val createdOrder = orderCommandHandler.createOrder(order)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdOrder.id).toUri()
        logger.info("Created order {} at uri {}", createdOrder, uri)
        return ResponseEntity.created(uri).body(createdOrder)
    }

    @GetMapping("/orders/{id}")
    fun createOrder(@PathVariable id: Long): ResponseEntity<OrderApi> {
        val order = orderCommandHandler.order(id = id)

        return ResponseEntity.ok(order)
    }
}

package com.example.restaurant.orderservice.infra.web

import com.example.restaurant.orderservice.application.api.OrderApi
import com.example.restaurant.orderservice.application.command.OrderCommandHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
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

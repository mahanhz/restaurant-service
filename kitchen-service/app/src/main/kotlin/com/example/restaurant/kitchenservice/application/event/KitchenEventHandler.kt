package com.example.restaurant.kitchenservice.application.event

import com.example.restaurant.common.event.ExportedEvent
import com.example.restaurant.kitchenservice.application.command.KitchenCommandHandler
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class KitchenEventHandler(
    private val kitchenCommandHandler: KitchenCommandHandler
) {
    @Async
    @EventListener
    fun handle(event: ExportedEvent) {
        if (event.type() == "OrderCreated") {
            val orderId = event.aggregateId()

            kitchenCommandHandler.assignChef(orderId.toLong())
        }
    }
}

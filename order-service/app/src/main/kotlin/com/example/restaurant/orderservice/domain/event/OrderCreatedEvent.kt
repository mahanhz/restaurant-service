package com.example.restaurant.orderservice.domain.event

import com.example.restaurant.common.event.ExportedEvent
import com.example.restaurant.orderservice.domain.Order
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class OrderCreatedEvent(private val id: Long, private val payload: JsonNode) : ExportedEvent {

    companion object {
        private val mapper = jacksonObjectMapper()

        fun of(order: Order): OrderCreatedEvent {
            val asJson: ObjectNode = mapper.createObjectNode()
                .put("id", order.id.value)
                .put("date", order.date.toString())
                .put("state", order.state.toString())

            val arrayNode = asJson.putArray("items")
            for (item in order.items) {
                arrayNode.add(
                    mapper.createObjectNode()
                        .put("name", item.name.value)
                        .put("quantity", item.quantity)
                )
            }

            return OrderCreatedEvent(order.id.value, asJson)
        }
    }

    override fun aggregateId(): String {
        return id.toString()
    }

    override fun aggregateType(): String {
        return "Order"
    }

    override fun payload(): JsonNode {
        return payload
    }

    override fun type(): String {
        return "OrderCreated"
    }
}

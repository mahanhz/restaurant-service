package com.example.restaurant.orderservice.integrationtest

import com.example.restaurant.orderservice.application.command.OrderCommandHandler
import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import com.example.restaurant.orderservice.domain.event.OrderCreatedEvent
import com.example.restaurant.orderservice.domain.service.OrderService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.RecordApplicationEvents
import java.time.LocalDate


@SpringBootTest
@RecordApplicationEvents
internal class CommandHandlerIT(
    @Autowired val orderService: OrderService,
    @Autowired val eventPublisher: ApplicationEventPublisher,
): DatabaseTest {
    @Autowired
    lateinit var events: ApplicationEvents

    @Test
    fun `should publish event when order is created`() {
        val commandHandler = OrderCommandHandler(orderService, eventPublisher)
        val order = commandHandler.createOrder(
            OrderApi(
                date= LocalDate.of(2023, 3, 5),
                items = setOf(ItemApi(name = "Burger", quantity = 1)),
            )
        )

        val orderCreatedEvents = events.stream(OrderCreatedEvent::class.java).toList()

        assertThat(orderCreatedEvents).hasSize(1)
        assertThat(orderCreatedEvents[0].aggregateId()).isEqualTo(order.id.toString())
        assertThat(orderCreatedEvents[0].aggregateType()).isEqualTo("Order")
        assertThat(orderCreatedEvents[0].type()).isEqualTo("OrderCreated")
    }
}
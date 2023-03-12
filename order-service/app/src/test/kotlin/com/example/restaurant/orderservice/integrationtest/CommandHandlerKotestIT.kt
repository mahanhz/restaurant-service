package com.example.restaurant.orderservice.integrationtest

import com.example.restaurant.orderservice.application.command.OrderCommandHandler
import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import com.example.restaurant.orderservice.domain.event.OrderCreatedEvent
import com.example.restaurant.orderservice.domain.service.OrderService
import io.kotest.assertions.json.FieldComparison
import io.kotest.assertions.json.compareJsonOptions
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldBeEqualIgnoringCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.context.event.ApplicationEvents
import org.springframework.test.context.event.RecordApplicationEvents
import java.time.LocalDate

@SpringBootTest
@RecordApplicationEvents
internal class CommandHandlerKotestIT(
    @Autowired val orderService: OrderService,
    @Autowired val eventPublisher: ApplicationEventPublisher,
): DatabaseTest, ShouldSpec() {
    @Autowired lateinit var events: ApplicationEvents

    init {
        should("publish event when order is created") {
            val commandHandler = OrderCommandHandler(orderService, eventPublisher)
            val order = commandHandler.createOrder(
                OrderApi(
                    date= LocalDate.of(2023, 3, 5),
                    items = setOf(ItemApi(name = "Burger", quantity = 1)),
                )
            )

            val orderCreatedEvents = events.stream(OrderCreatedEvent::class.java).toList()

            orderCreatedEvents shouldHaveSize 1
            orderCreatedEvents[0].aggregateId() shouldBeEqualIgnoringCase  order.id.toString()
            orderCreatedEvents[0].aggregateType() shouldBeEqualIgnoringCase "Order"
            orderCreatedEvents[0].type() shouldBeEqualIgnoringCase "OrderCreated"
            orderCreatedEvents[0].payload().toString().shouldEqualJson(
                "{\"date\":\"2023-03-05\",\"state\":\"CREATED\",\"items\":[{\"name\":\"Burger\",\"quantity\":1}]}",
                compareJsonOptions { fieldComparison = FieldComparison.Lenient }
            )
        }
    }
}
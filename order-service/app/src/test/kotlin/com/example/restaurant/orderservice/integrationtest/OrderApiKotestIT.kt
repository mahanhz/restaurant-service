package com.example.restaurant.orderservice.integrationtest

import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import io.kotest.assertions.json.shouldContainJsonKeyValue
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.time.LocalDate


@JsonTest
internal class OrderApiKotestIT(
    @Autowired private val json: JacksonTester<OrderApi>
): IntegrationTest, ShouldSpec ({
    should("serialize") {
        val orderApi = OrderApi(
            id = 1234, date = LocalDate.of(2023, 3, 5),
            items = setOf(ItemApi(name = "Burger", quantity = 1)),
        )

        json.write(orderApi).json.shouldContainJsonKeyValue("@.id", 1234)
        json.write(orderApi).json.shouldContainJsonKeyValue("@.date", "2023-03-05")
        json.write(orderApi).json.shouldContainJsonKeyValue("@.items[0].name", "Burger")
        json.write(orderApi).json.shouldContainJsonKeyValue("@.items[0].quantity", 1)
    }

    should("deserialize") {
        val content = "{\"id\":1234,\"date\":\"2023-03-05\",\"items\":[{\"name\":\"Burger\",\"quantity\":1}]}"
        json.parse(content).`object` shouldBe OrderApi(
            id = 1234,
            date = LocalDate.of(2023, 3, 5),
            items = setOf(ItemApi(name = "Burger", quantity = 1)),
        )
    }
})
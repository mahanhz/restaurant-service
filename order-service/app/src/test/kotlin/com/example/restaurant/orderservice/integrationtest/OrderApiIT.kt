package com.example.restaurant.orderservice.integrationtest

import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import com.example.restaurant.orderservice.domain.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import java.time.LocalDate


@JsonTest
internal class OrderApiIT: IntegrationTest {
    @Autowired
    private val json: JacksonTester<OrderApi>? = null

    @Test
    fun `should serialize`() {
        val orderApi = OrderApi(
            id = 1234,
            date = LocalDate.of(2023, 3, 5),
            items = setOf(ItemApi(name = "Burger", quantity = 1)),
            state = State.CREATED.toString()
        )

        assertThat(json!!.write(orderApi)).extractingJsonPathNumberValue("@.id").isEqualTo(1234)
        assertThat(json.write(orderApi)).extractingJsonPathStringValue("@.date").isEqualTo("2023-03-05")
        assertThat(json.write(orderApi)).extractingJsonPathStringValue("@.items[0].name").isEqualTo("Burger")
        assertThat(json.write(orderApi)).extractingJsonPathNumberValue("@.items[0].quantity").isEqualTo(1)
        assertThat(json.write(orderApi)).extractingJsonPathStringValue("@.state").isEqualTo("CREATED")
    }

    @Test
    fun `should deserialize`() {
        val content = "{\"id\":1234,\"date\":\"2023-03-05\",\"state\":\"CREATED\",\"items\":[{\"name\":\"Burger\",\"quantity\":1}]}"
        assertThat(json!!.parse(content)).isEqualTo(
            OrderApi(
                id = 1234,
                date = LocalDate.of(2023, 3, 5),
                items = setOf(ItemApi(name = "Burger", quantity = 1)),
                state = "CREATED"
            )
        )
    }
}
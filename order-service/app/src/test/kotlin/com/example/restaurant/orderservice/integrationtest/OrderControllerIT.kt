package com.example.restaurant.orderservice.integrationtest

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.example.restaurant.orderservice.application.command.OrderCommandHandler
import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import com.example.restaurant.orderservice.infra.web.OrderController
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate


@WebMvcTest(OrderController::class)
internal class OrderControllerIT(@Autowired val mockMvc: MockMvc): IntegrationTest {

	@MockBean
	private var commandHandler: OrderCommandHandler? = null

	private val mapper = jacksonObjectMapper().registerModule(JavaTimeModule())

	@Test
	fun `should create order`() {
		val orderApiRequest = OrderApi(
			date = LocalDate.of(2023, 3, 5),
			items = setOf(ItemApi(name = "Burger", quantity = 1)),
		)
		val orderApiResponse = OrderApi(
			id = 1234, date = LocalDate.of(2023, 3, 5),
			items = setOf(ItemApi(name = "Burger", quantity = 1)),
			state = "CREATED"
		)
		given(commandHandler?.createOrder(orderApiRequest)).willReturn(orderApiResponse)

		mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(orderApiRequest)))
			.andExpect(status().isCreated)
			.andExpect(jsonPath("$.id").value("1234"))
			.andExpect(jsonPath("$.date").value("2023-03-05"))
			.andExpect(jsonPath("$.items[0].name").value("Burger"))
			.andExpect(jsonPath("$.items[0].quantity").value("1"))
			.andExpect(jsonPath("$.state").value("CREATED"))
	}
}

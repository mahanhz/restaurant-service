package com.example.restaurant.orderservice.integrationtest

import com.example.restaurant.orderservice.domain.State
import com.example.restaurant.orderservice.infra.persistence.ItemEntity
import com.example.restaurant.orderservice.infra.persistence.OrderEntity
import com.example.restaurant.orderservice.infra.persistence.OrderRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDate


@DataJpaTest
internal class OrderRepositoryIT(
	@Autowired val entityManager: TestEntityManager,
	@Autowired val repository: OrderRepository
): DatabaseTest {
	@Test
	fun `should create a order with items`() {
		val orderName = "Burger"
		val orderDate = LocalDate.of(2023, 3, 5)

		val itemEntity = ItemEntity(name = orderName, quantity = 1)
		entityManager.persist(itemEntity)

		val orderEntity = OrderEntity(date = orderDate, items = setOf(itemEntity), state = State.CREATED)
		val savedOrder = entityManager.persist(orderEntity)

		val order = repository.findById(savedOrder.id!!)
		assertThat(order.get().date).isEqualTo(orderDate)
		assertThat(order.get().state).isEqualTo(State.CREATED)
		assertThat(order.get().items!!.elementAt(0).name).isEqualTo(orderName)
		assertThat(order.get().items!!.elementAt(0).quantity).isEqualTo(1)
	}
}

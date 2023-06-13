package com.example.restaurant.orderservice.integrationtest

import com.example.restaurant.orderservice.domain.State
import com.example.restaurant.orderservice.infra.persistence.ItemEntity
import com.example.restaurant.orderservice.infra.persistence.OrderEntity
import com.example.restaurant.orderservice.infra.persistence.OrderRepository
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDate

@DataJpaTest
internal class OrderRepositoryKotestIT(
    @Autowired val entityManager: TestEntityManager,
    @Autowired val repository: OrderRepository
) : DatabaseTest, ShouldSpec({

    should("create a order with items") {
        val orderName = "Burger"
        val orderDate = LocalDate.of(2023, 3, 5)

        val itemEntity = ItemEntity(name = orderName, quantity = 1)
        entityManager.persist(itemEntity)

        val orderEntity = OrderEntity(date = orderDate, items = setOf(itemEntity), state = State.CREATED)
        val savedOrder = entityManager.persist(orderEntity)

        val order = repository.findById(savedOrder.id!!)
        order.get().date shouldBe orderDate
        order.get().state shouldBe State.CREATED
        order.get().items!!.elementAt(0).name shouldBe orderName
        order.get().items!!.elementAt(0).quantity shouldBe 1
    }
})

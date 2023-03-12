package com.example.restaurant.kitchenservice.steps

import com.example.restaurant.kitchenservice.application.api.ChefApi
import com.example.restaurant.kitchenservice.application.api.DetailedChefApi
import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.web.client.TestRestTemplate
import java.time.LocalDate


class KitchenSteps (private val restTemplate: TestRestTemplate) {

    private var order: OrderApi? = null
    private var chef: ChefApi? = null

    @Given("chef {string} is available")
    fun chefChefNameIsAvailable(chefName: String) {
        val chefResponse = restTemplate.postForEntity(
            "/chefs",
            ChefApi(chef = chefName),
            ChefApi::class.java
        )
        chef = chefResponse.body
    }

    @When("an order for a {string} is created")
    fun anOrderWithOrderId(itemName: String) {
        val orderResponse = restTemplate.postForEntity(
            "/orders",
            OrderApi(date = LocalDate.now(), items = setOf(ItemApi(name = itemName, quantity = 1))),
            OrderApi::class.java
        )
        order = orderResponse.body
    }

    @Then("the order for a {string} is assigned to chef {string}")
    fun theOrderIsAssignedToChefChefName(itemName: String, chefName: String) {
        val chefResponse = restTemplate.getForEntity(
            "/chefs/${chef!!.id!!}",
            DetailedChefApi::class.java
        )
        assertThat(chefResponse.body!!.chef).isEqualTo(chefName)
        assertThat(chefResponse.body!!.orders).hasSize(1)
        assertThat(chefResponse.body!!.orders!!.first().id).isEqualTo(order!!.id!!)
        assertThat(chefResponse.body!!.orders!!.first().name).isEqualTo(itemName)
    }
}

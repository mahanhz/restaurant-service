package com.example.restaurant.orderservice.steps

import com.example.restaurant.orderservice.application.api.ItemApi
import com.example.restaurant.orderservice.application.api.OrderApi
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import java.time.LocalDate


class OrderSteps (private val restTemplate: TestRestTemplate) {

    private var response: ResponseEntity<OrderApi>? = null

    @When("creating an order for item {string} and date {string}")
    fun creating_an_order(name: String, date: String) {
        response = restTemplate.postForEntity(
            "/orders",
            OrderApi(date = LocalDate.parse(date), items = setOf(ItemApi(name = name, quantity = 1))),
            OrderApi::class.java
        )
    }

    @Then("the order should be created")
    fun the_order_should_be_created() {
        assertThat(response!!.body!!.state).isEqualTo("CREATED")
    }

    @Then("the order should contain the item {string} and date {string}")
    fun the_order_should_contain_an_item(name: String, date: String) {
        assertThat(response!!.body!!.date).isEqualTo(date)
        assertThat(response!!.body!!.items.elementAt(0).name).isEqualTo(name)
        assertThat(response!!.body!!.items.elementAt(0).quantity).isEqualTo(1)
    }

    @Then("the order should have an identity")
    fun the_order_should_have_an_identity() {
        assertThat(response!!.body!!.id).isNotNull
    }
}

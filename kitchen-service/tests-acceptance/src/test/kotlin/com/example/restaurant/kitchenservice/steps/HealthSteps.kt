package com.example.restaurant.kitchenservice.steps

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity

class HealthSteps (private val restTemplate: TestRestTemplate) {

    private var response: ResponseEntity<String>? = null

    @When("^calling the health endpoint$")
    fun callingTheHealthEndpoint() {
        response = restTemplate.getForEntity("/actuator/health", String::class.java)
    }

    @Then("^the response should be healthy$")
    fun theResponseShouldBeHealthy() {
        assertThat(response?.statusCodeValue).isEqualTo(200)
    }
}

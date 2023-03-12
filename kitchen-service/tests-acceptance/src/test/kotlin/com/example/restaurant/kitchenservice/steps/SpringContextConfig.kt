package com.example.restaurant.kitchenservice.steps

import com.example.restaurant.kitchenservice.config.WorldConfig
import com.example.restaurant.kitchenservice.model.World
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import jakarta.annotation.PostConstruct

const val HOST_URL_FORMAT = "http://localhost:%s"

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(value = [WorldConfig::class])
@ActiveProfiles("acceptancetest")
class SpringContextConfig: DatabaseTest() {

    @LocalServerPort
    private var serverPort: Int? = 0

    @Autowired
    private lateinit var world: World

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @PostConstruct
    fun postConstruct() {
        world.baseUrl = String.format(HOST_URL_FORMAT, serverPort)
    }
}

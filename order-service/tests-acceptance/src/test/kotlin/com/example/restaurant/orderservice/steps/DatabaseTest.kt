package com.example.restaurant.orderservice.steps

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName


open class DatabaseTest {

    companion object {
        private val dockerImageName: DockerImageName? =
            DockerImageName.parse("mysql:5.7").asCompatibleSubstituteFor("mysql")
        private val container = MySQLContainer<Nothing>(dockerImageName)
            .apply {
                start()
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.password", container::getPassword);
            registry.add("spring.datasource.username", container::getUsername);
        }
    }
}
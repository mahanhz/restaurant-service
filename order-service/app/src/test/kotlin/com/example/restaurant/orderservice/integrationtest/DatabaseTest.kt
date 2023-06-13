package com.example.restaurant.orderservice.integrationtest

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@Transactional
interface DatabaseTest : IntegrationTest {

    companion object {
        private val dockerImageName: DockerImageName? =
            DockerImageName.parse("percona:5.7").asCompatibleSubstituteFor("mysql")
        private val container = MySQLContainer<Nothing>(dockerImageName)
            .apply {
                start()
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.password", container::getPassword)
            registry.add("spring.datasource.username", container::getUsername)
        }
    }
}

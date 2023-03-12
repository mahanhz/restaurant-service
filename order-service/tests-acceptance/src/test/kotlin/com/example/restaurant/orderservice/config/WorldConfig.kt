package com.example.restaurant.orderservice.config

import com.example.restaurant.orderservice.model.World
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope

@TestConfiguration
class WorldConfig {

    @Scope("cucumber-glue")
    @Bean
    fun world(): World {
        return World()
    }
}

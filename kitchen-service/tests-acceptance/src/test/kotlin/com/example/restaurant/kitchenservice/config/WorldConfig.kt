package com.example.restaurant.kitchenservice.config

import com.example.restaurant.kitchenservice.model.World
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

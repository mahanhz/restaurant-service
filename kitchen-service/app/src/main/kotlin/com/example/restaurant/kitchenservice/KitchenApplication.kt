package com.example.restaurant.kitchenservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = ["com.example.restaurant"])
class KitchenApplication

fun main(args: Array<String>) {
    runApplication<KitchenApplication>(*args)
}

@Configuration
@EnableJpaRepositories(basePackages = ["com.example.restaurant.kitchenservice"])
class KitchenJpaConfig
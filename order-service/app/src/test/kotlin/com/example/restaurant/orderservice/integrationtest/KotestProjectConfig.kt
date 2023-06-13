package com.example.restaurant.orderservice.integrationtest

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class KotestProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)
}

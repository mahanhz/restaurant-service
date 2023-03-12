package com.example.restaurant.orderservice.integrationtest

import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles(profiles = ["integrationtest"])
interface IntegrationTest
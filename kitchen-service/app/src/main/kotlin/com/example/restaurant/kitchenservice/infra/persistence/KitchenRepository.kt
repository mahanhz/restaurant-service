package com.example.restaurant.kitchenservice.infra.persistence

import org.springframework.data.repository.CrudRepository
import java.util.*

internal interface ChefRepository : CrudRepository<ChefEntity, Long> {
    fun findByName(name: String?): Optional<ChefEntity>

}
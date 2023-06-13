package com.example.restaurant.kitchenservice.infra.web

import com.example.restaurant.kitchenservice.application.api.ChefApi
import com.example.restaurant.kitchenservice.application.api.DetailedChefApi
import com.example.restaurant.kitchenservice.application.command.KitchenCommandHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
internal class KitchenController(private val kitchenCommandHandler: KitchenCommandHandler) {

    val logger: Logger = LoggerFactory.getLogger(KitchenController::class.java)

    @PostMapping("/chefs")
    fun createChef(@RequestBody chefApi: ChefApi): ResponseEntity<ChefApi> {
        val chef = kitchenCommandHandler.createChef(chefApi.chef!!)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chef.id).toUri()

        return ResponseEntity.created(uri).body(chef)
    }

    @GetMapping("/chefs/{id}")
    fun chef(@PathVariable id: Long): ResponseEntity<DetailedChefApi> {
        val chef = kitchenCommandHandler.chef(id = id)

        return ResponseEntity.ok(chef)
    }

    @PostMapping("/chefs/assign")
    fun assignChef(@RequestBody orderId: Long): ResponseEntity<ChefApi> {
        val chef = kitchenCommandHandler.assignChef(orderId)

        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chef.id).toUri()

        return ResponseEntity.created(uri).body(chef)
    }
}

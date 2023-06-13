package com.example.restaurant.orderservice.infra.persistence

import org.springframework.data.repository.CrudRepository

internal interface OrderRepository : CrudRepository<OrderEntity, Long>

internal interface ItemRepository : CrudRepository<ItemEntity, Long>

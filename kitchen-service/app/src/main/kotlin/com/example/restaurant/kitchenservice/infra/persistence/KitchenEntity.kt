package com.example.restaurant.kitchenservice.infra.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "chefs")
internal class ChefEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false) var name: String? = null,
    @OneToMany(targetEntity = OrderIdEntity::class)
    @JoinTable(
        name = "chefs_orders",
        joinColumns = [JoinColumn(name = "chef_id")],
        inverseJoinColumns = [JoinColumn(name = "order_id")]
    )
    var orders: Set<OrderIdEntity>? = HashSet(),
    @CreatedDate
    @Column(name = "created_date")
    var createdDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(name = "last_modified_date")
    var lastModifiedDate: LocalDateTime = LocalDateTime.now()
)

@Entity
@Table(name = "orders")
internal class OrderIdEntity(
    @Id var id: Long? = null
)

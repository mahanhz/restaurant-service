package com.example.restaurant.orderservice.infra.persistence

import com.example.restaurant.orderservice.domain.State
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
internal class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "order_date", nullable = false) var date: LocalDate? = null,
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    var state: State? = null,
    @ManyToMany(targetEntity = ItemEntity::class)
    @JoinTable(
        name = "orders_items",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: Set<ItemEntity>? = HashSet(),
    @CreatedDate
    @Column(name = "created_date")
    var createdDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(name = "last_modified_date")
    var lastModifiedDate: LocalDateTime = LocalDateTime.now()
)

@Entity
@Table(name = "items")
internal class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false) var name: String? = null,
    @Column(nullable = false) var quantity: Int? = null,
    @CreatedDate
    @Column(name = "created_date")
    var createdDate: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    @Column(name = "last_modified_date")
    var lastModifiedDate: LocalDateTime = LocalDateTime.now()
)

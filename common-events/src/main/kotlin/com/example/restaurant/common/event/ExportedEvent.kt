package com.example.restaurant.common.event

import com.fasterxml.jackson.databind.JsonNode

interface ExportedEvent {
    fun aggregateId(): String
    fun aggregateType(): String
    fun payload(): JsonNode
    fun type(): String
}
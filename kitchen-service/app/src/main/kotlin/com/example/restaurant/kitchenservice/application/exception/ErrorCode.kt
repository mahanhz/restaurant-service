package com.example.restaurant.kitchenservice.application.exception

enum class ErrorCode(val message: String) {
    ERR_KIT_SER_1("Chef not found"),
    ERR_KIT_SER_2("Chef not available")
}
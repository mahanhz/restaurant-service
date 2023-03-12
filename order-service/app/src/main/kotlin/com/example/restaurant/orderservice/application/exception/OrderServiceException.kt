package com.example.restaurant.orderservice.application.exception

open class OrderServiceException: RuntimeException {
    val code: ErrorCode

    constructor(code: ErrorCode) : super() {
        this.code = code
    }

    constructor(message: String, code: ErrorCode) : super(message) {
        this.code = code
    }
    constructor(message: String, cause: Throwable, code: ErrorCode) : super(message, cause) {
        this.code = code
    }
    constructor(cause: Throwable, code: ErrorCode) : super(cause) {
        this.code = code
    }
}
package com.example.restaurant.kitchenservice.application.exception

open class KitchenServiceException : RuntimeException {
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

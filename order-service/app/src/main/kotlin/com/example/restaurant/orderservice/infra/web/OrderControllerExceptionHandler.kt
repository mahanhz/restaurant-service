package com.example.restaurant.orderservice.infra.web

import com.example.restaurant.orderservice.application.exception.OrderServiceException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice(basePackages = ["com.example.restaurant.orderservice"])
internal class OrderControllerExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(OrderControllerExceptionHandler::class.java)

    @ExceptionHandler(value = [Exception::class])
    fun catchAll(ex: OrderServiceException, request: WebRequest): ResponseEntity<Map<String, Any>>? {
        logger.error("Caught exception", ex)

        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["status"] = "${HttpStatus.INTERNAL_SERVER_ERROR.value()} - ${HttpStatus.INTERNAL_SERVER_ERROR.name}"
        body["code"] = ex.code
        body["message"] = ex.code.message
        body["path"] = (request as ServletWebRequest).request.requestURI.toString()

        return ResponseEntity<Map<String, Any>>(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

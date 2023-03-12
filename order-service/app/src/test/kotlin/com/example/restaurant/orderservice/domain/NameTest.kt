package com.example.restaurant.orderservice.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NameTest {

    @ParameterizedTest
    @MethodSource("validNames")
    fun `should allow creation with valid names`(value: String) {
        assertThat(Name(value)).isNotNull
    }

    @ParameterizedTest
    @MethodSource("invalidNames")
    fun `should not allow creation with invalid names`(value: String) {
        assertThatThrownBy { Name(value) }.isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    companion object {
        @JvmStatic
        fun validNames(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("Jo"),
                Arguments.of("b".repeat(200))
            )
        }

        @JvmStatic
        fun invalidNames(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(""),
                Arguments.of("a"),
                Arguments.of("b".repeat(201))
            )
        }
    }
}
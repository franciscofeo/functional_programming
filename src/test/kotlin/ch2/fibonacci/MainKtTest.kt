package ch2.fibonacci

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MainKtTest {

    @ParameterizedTest
    @CsvSource("1, 1", "5, 5", "7, 13")
    fun `should calculate nth Fibonacci's number`(n: Int, res: Int) {
        assertEquals(res, fib(n))
    }
}
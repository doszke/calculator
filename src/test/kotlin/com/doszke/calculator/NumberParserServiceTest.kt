package com.doszke.calculator

import com.doszke.calculator.service.NumberParserService
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.NumberFormatException

@SpringBootTest
class NumberParserServiceTest {


    @Autowired
    private lateinit var numberParserService: NumberParserService

    private val data = listOf(
            arrayOf("10", "12", ""),
            arrayOf("5.4", "2.3", "0.3"),
            arrayOf("hello", "spring", "boot")
    )

    private val output = listOf(
            arrayOf(10.0, 12.0),
            arrayOf(5.4, 2.3, 0.3)
    )


    @Test
    fun testParse(){
        for(x in 0..1) {
            val otp = numberParserService.parse(data[x][0], data[x][1], data[x][2])
            assertThat(otp).containsExactlyElementsOf(output[x].toList())
        }
    }

    @Test
    fun testInvalidParse(){
        assertThatThrownBy {
            val otp = numberParserService.parse(data[2][0], data[2][1], data[2][2])
        }.isInstanceOf(NumberFormatException::class.java)
    }

}
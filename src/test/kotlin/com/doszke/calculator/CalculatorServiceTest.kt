package com.doszke.calculator

import com.doszke.calculator.service.CalculatorService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CalculatorServiceTest {

    @Autowired
    private lateinit var calculatorService: CalculatorService

    @Test
    fun arithmeticTest(){
        calculatorService.precision = 6
        val values = arrayOf(3.0, 3.0, 2.0) //res = 8/3 = 2.66666....
        val result1 = calculatorService.arithmetic(values)
        assertThat(result1).isEqualTo(2.666667)
        calculatorService.precision = 0
        val result2 = calculatorService.arithmetic(values)
        assertThat(result2).isEqualTo(3.0)
    }

    @Test
    fun geometricTest(){
        calculatorService.precision = 6
        val values = arrayOf(3.0, 3.0, 2.0) //res = 2.62074139
        val result1 = calculatorService.geometric(values)
        assertThat(result1).isEqualTo(2.620741)
        calculatorService.precision = 3
        val result2 = calculatorService.geometric(values)
        assertThat(result2).isEqualTo(2.621)
    }

    @Test
    fun harmonicTest(){
        calculatorService.precision = 6
        val values = arrayOf(3.0, 3.0, 2.0) //res = 2.5714285714
        val result1 = calculatorService.harmonic(values)
        assertThat(result1).isEqualTo(2.571429)
        calculatorService.precision = 0
        val result2 = calculatorService.harmonic(values)
        assertThat(result2).isEqualTo(3.0)
    }

}
package com.doszke.calculator

import com.doszke.calculator.engine.CalculatorEngine
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.math.pow
import kotlin.math.abs

@SpringBootTest
class CalculatorEngineTest {

    @Autowired
    private lateinit var engine: CalculatorEngine

    private val offset = Offset.offset(0.0001)

    @Test
    fun testArithmetic(){
        val input = arrayOf(1.0, 2.0, 3.0)
        val c = engine.arithmetic(input)
        assertThat(2.0).isEqualTo(c)
    }

    @Test
    fun testGeometric(){
        val input = arrayOf(1.0, 2.0, 3.0)
        val c = engine.geometric(input)
        assertThat(6.0.pow(1.0/3)).isCloseTo(c, offset)
    }

    @Test
    fun testHarmonic(){
        val input = arrayOf(1.0, 2.0, 3.0)
        val c = engine.harmonic(input)
        val harmonic = 3.0 / (1.0 + 1.0/2.0 + 1.0/3.0)
        assertThat(harmonic).isCloseTo(c, offset)
    }

}
package com.doszke.calculator

import com.doszke.calculator.engine.CalculatorEngine
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.math.pow
import kotlin.math.abs

@SpringBootTest
class CalculatorEngineTest {

    @Autowired
    private lateinit var engine: CalculatorEngine

    private val errorRate = 0.0001

    @Test
    fun testArithmetic(){
        val input = arrayOf(1.0, 2.0, 3.0)
        val c = engine.arithmetic(input)
        assert(2.0 == c)
    }

    @Test
    fun testGeometric(){
        val input = arrayOf(1.0, 2.0, 3.0)
        val c = engine.geometric(input)
        assert(abs(6.0.pow(1.0/3) - c) < errorRate)
    }

    @Test
    fun testHarmonic(){
        val input = arrayOf(1.0, 2.0, 3.0)
        val c = engine.harmonic(input)
        val harmonic = 3 / (1 + 1.0/2.0 + 1.0/3.0)
        assert(abs(harmonic - c) < errorRate)
    }

}
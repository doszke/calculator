package com.doszke.calculator.service

import com.doszke.calculator.engine.CalculatorEngine
import org.springframework.stereotype.Service
import kotlin.math.pow

/**
 * Service used for calculating means and rounding the results to the desired precision.
 *
 * @property precision precision
 */
@Service
class CalculatorService(private val calculatorEngine: CalculatorEngine) {

    var precision: Int = 1000
        set(value) {field = (10.0).pow(value).toInt()}

    /**
     * Method defining arithmetic mean.
     * @param args array of numbers
     * @return arithmetic mean
     */
    fun arithmetic(args: Array<Double>): Double {
        val otp = calculatorEngine.arithmetic(args)
        return this.round(otp)
    }

    /**
     * Method defining geometric mean.
     * @param args array of numbers
     * @return geometric mean
     */
    fun geometric(args: Array<Double>): Double {
        val otp = calculatorEngine.geometric(args)
        return this.round(otp)
    }

    /**
     * Method defining harmonic mean.
     * @param args array of numbers
     * @return harmonic mean
     */
    fun harmonic(args: Array<Double>): Double {
        val otp = calculatorEngine.harmonic(args)
        return this.round(otp)
    }

    /**
     * Method used for rounding the result to desired [precision]
     * @param x value to be rounded
     * @return rounded [x]
     */
    private fun round(x: Double): Double {
        return kotlin.math.round(x * precision) / precision
    }

}


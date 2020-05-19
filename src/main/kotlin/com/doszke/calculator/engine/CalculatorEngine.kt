package com.doszke.calculator.engine

/**
 * Interface defining multiple means.
 */
interface CalculatorEngine {

    fun arithmetic(args: Array<Double>): Double
    fun geometric(args: Array<Double>): Double
    fun harmonic(args: Array<Double>): Double

}

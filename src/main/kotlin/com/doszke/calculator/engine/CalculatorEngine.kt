package com.doszke.calculator.engine

interface CalculatorEngine {

    fun arithmetic(args: Array<Double>): Double
    fun geometric(args: Array<Double>): Double
    fun harmonic(args: Array<Double>): Double

}

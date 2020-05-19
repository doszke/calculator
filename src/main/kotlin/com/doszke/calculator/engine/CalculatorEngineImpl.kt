package com.doszke.calculator.engine

import org.springframework.stereotype.Component
import kotlin.math.pow

/**
 * Class defining arithmetic, geometric and harmonic mean.
 */
@Component("calculatorEngine")
class CalculatorEngineImpl : CalculatorEngine {

    /**
     * Method defining arithmetic mean.
     * @param args array of numbers
     * @return arithmetic mean
     */
    override fun arithmetic(args: Array<Double>): Double {
        return args.sum() / (args.size)
    }

    /**
     * Method defining geometric mean.
     * @param args array of numbers
     * @return geometric mean
     */
    override fun geometric(args: Array<Double>): Double {
        var tmp = 1.0
        args.forEach { tmp *= it }
        return tmp.pow(1.0/(args.size))
    }

    /**
     * Method defining harmonic mean.
     * @param args array of numbers
     * @return harmonic mean
     */
    override fun harmonic(args: Array<Double>): Double {
        val sumOfInverted = args.map{ 1/it }.sum()
        return (args.size).toDouble() / sumOfInverted
    }
    
}

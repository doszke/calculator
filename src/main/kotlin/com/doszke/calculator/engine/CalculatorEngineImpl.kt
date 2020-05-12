package com.doszke.calculator.engine

import org.springframework.stereotype.Component
import kotlin.math.pow

@Component("calculatorEngine")
class CalculatorEngineImpl : CalculatorEngine {

    override fun arithmetic(args: Array<Double>): Double {
        return args.sum() / (args.size)
    }

    override fun geometric(args: Array<Double>): Double {
        var tmp = 1.0
        args.forEach { tmp *= it }
        return tmp.pow(1.0/(args.size))
    }

    override fun harmonic(args: Array<Double>): Double {
        val sumOfInverted = args.map{ 1/it }.sum()
        return (args.size).toDouble() / sumOfInverted
    }
    
}

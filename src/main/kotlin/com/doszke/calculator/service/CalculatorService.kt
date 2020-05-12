package com.doszke.calculator.service

import com.doszke.calculator.engine.CalculatorEngine
import org.springframework.stereotype.Service

@Service
class CalculatorService(private val calculatorEngine: CalculatorEngine) {

    //może troche strzał z armaty do muchy, ale serwis pełni rolę fasady
    //i jest miejscem zapewniania implementacji kilku komponentów w jednej metodzie
    //taki zabieg ma na celu przede wszystkim ukrywanie implementacji przed użytkownikiem końcowym komponentu

    fun arithmetic(args: Array<Double>): Double {
        return calculatorEngine.arithmetic(args)
    }

    fun geometric(args: Array<Double>): Double {
        return calculatorEngine.geometric(args)
    }

    fun harmonic(args: Array<Double>): Double {
        return calculatorEngine.harmonic(args)
    }
}


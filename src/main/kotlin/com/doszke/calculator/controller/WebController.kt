package com.doszke.calculator.controller

import com.doszke.calculator.service.CalculatorService
import com.doszke.calculator.service.NumberParserService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.lang.ArithmeticException
import kotlin.math.floor

@Controller
class WebController(
        private val calculatorService : CalculatorService,
        private val numberParserService: NumberParserService
) {

    private val warning: String = "Cannot calculate harmonic mean, when at least one argument is zero!"

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @PostMapping("/")
    fun calculate(
            @RequestParam("a") a : String,
            @RequestParam("b") b: String,
            @RequestParam("c") c: String,
            @RequestParam("mode") mode: String,
            model : ModelMap
    ) : String {
        val res : Double
        try {
            val args = numberParserService.parse(a, b, c)
            res = when(mode) {
                "arithmetic" -> calculatorService.arithmetic(args)
                "geometric" -> calculatorService.geometric(args)
                "harmonic" -> {
                    if (args.contains(0.0)) throw ArithmeticException(warning)
                    calculatorService.harmonic(args)
                }
                else -> throw IllegalArgumentException("Unsupported operation")
            }
        } catch (e: Exception) {
            model["error"] = if (e.message == warning) warning else "Invalid data passed! "
            return "index"
        }
        model["res"] = (floor(res*1000)/1000).toString()
        return "index"
    }


}





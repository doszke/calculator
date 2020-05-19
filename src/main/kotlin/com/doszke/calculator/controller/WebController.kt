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
import kotlin.math.pow

/**
 * Main web controller.
 *
 * @property calculatorService calculator service
 * @property numberParserService service used for number parsing
 * @property warning text property storing information about passing zero to harmonic mean
 *
 * @constructor primary constructor
 */
@Controller
class WebController(
        private val calculatorService : CalculatorService,
        private val numberParserService: NumberParserService
) {

    private val warning: String = "Cannot calculate harmonic mean, when at least one argument is zero!"

    /**
     * Index GET mapping method.
     * @return 'index' template
     */
    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    /**
     * Index POST mapping method. It parses given Strings to number, performs selected operation and rounds the result to the desired accuracy.
     * @param a first number
     * @param b second number
     * @param c third, optional number
     * @param mode a String indicating which mean will be calculated
     * @param precision precision
     * @param model model map instance
     * @return 'index' template
     */
    @PostMapping("/")
    fun calculate(
            @RequestParam("a") a : String,
            @RequestParam("b") b: String,
            @RequestParam("c") c: String,
            @RequestParam("mode") mode: String,
            @RequestParam("precision") precision: Int,
            model : ModelMap
    ) : String {
        val res : Double
        try {
            val args = numberParserService.parse(a, b, c) //parse the numbers
            calculatorService.precision = precision //set the precision
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
        model["res"] = res.toString()
        return "index"
    }


}





package com.doszke.calculator.service

import org.springframework.stereotype.Service

/**
 * Class used for parsing numbers passed by the user.
 */
@Service
class NumberParserService {

    /**
     * Method used for parsing numbers passed by the user.
     * @param a first String
     * @param b second String
     * @param c third String
     * @return array of doubles
     */
    fun parse(a: String, b: String, c: String): Array<Double> {
        val aa = a.toDouble()
        val bb = b.toDouble()
        val cc = if (c == "") Double.NaN else c.toDouble() //third is optional
        return if (cc.isNaN()) arrayOf(aa, bb) else arrayOf(aa, bb, cc) //if third is NaN, array of two, otherwise array of three doubles
    }

}
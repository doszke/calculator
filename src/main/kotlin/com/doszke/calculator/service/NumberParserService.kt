package com.doszke.calculator.service

import org.springframework.stereotype.Service

@Service
class NumberParserService {

    fun parse(a: String, b: String, c: String): Array<Double> {
        val aa = a.toDouble()
        val bb = b.toDouble()
        val cc = if (c == "") Double.NaN else c.toDouble() //third is optional
        return if (cc.isNaN()) arrayOf(aa, bb) else arrayOf(aa, bb, cc) //if third is NaN, array of two, otherwise array of three doubles
    }

}
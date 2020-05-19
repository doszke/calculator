package com.doszke.calculator

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class WebTierTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testIndexGet(){
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("Mean calculator"))) //header of index.html
    }

    @Test
    fun testIndexPost(){
        listOf("arithmetic", "geometric", "harmonic").forEach {//'it' refers to items in lists
            mockMvc.perform(
                    post("/")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("a", "10")
                            .param("b", "11")
                            .param("c", "12")
                            .param("mode", it)
                            .param("precision", "5")
            ).andExpect(status().isOk).andExpect(content().string(containsString("Result: ")))

            //2 numbers
            mockMvc.perform(
                    post("/")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("a", "10")
                            .param("b", "11")
                            .param("c", "")
                            .param("mode", it)
                            .param("precision", "5")
            ).andExpect(status().isOk).andExpect(content().string(containsString("Result: ")))

            //1 number
            mockMvc.perform(
                    post("/")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("a", "10")
                            .param("b", "")
                            .param("c", "")
                            .param("mode", it)
                            .param("precision", "5")
            ).andExpect(status().isOk).andExpect(content().string(containsString("Invalid data passed!")))

            //some text passed
            mockMvc.perform(
                    post("/")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("a", "hello")
                            .param("b", "spring")
                            .param("c", "boot")
                            .param("mode", it)
                            .param("precision", "5")
            ).andExpect(status().isOk).andExpect(content().string(containsString("Invalid data passed!")))

        }
    }

    @Test
    fun testIndexPostHarmonicZeroValue(){
        //3 numbers
        mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("a", "0")
                        .param("b", "11")
                        .param("c", "12")
                        .param("mode", "harmonic")
                        .param("precision", "5")
        ).andExpect(status().isOk).andExpect(content().string(containsString(
                "Cannot calculate harmonic mean, when at least one argument is zero!"
        )))

        //2 numbers
        mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("a", "10")
                        .param("b", "0")
                        .param("c", "")
                        .param("mode", "harmonic")
                        .param("precision", "5")
        ).andExpect(status().isOk).andExpect(content().string(containsString(
                "Cannot calculate harmonic mean, when at least one argument is zero!"
        )))

        //3 numbers and third is zero
        mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("a", "10")
                        .param("b", "10")
                        .param("c", "0")
                        .param("mode", "harmonic")
                        .param("precision", "5")
        ).andExpect(status().isOk).andExpect(content().string(containsString(
                "Cannot calculate harmonic mean, when at least one argument is zero!"
        )))
    }

}
package com.doszke.calculator

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver


@Configuration
class ThymeleafConfig {
    @Bean
    fun templateResolver(): SpringResourceTemplateResolver {
        val templateResolver = SpringResourceTemplateResolver()
        templateResolver.isCacheable = false
        templateResolver.prefix = "classpath:/templates/"
        templateResolver.suffix = ".html"
        return templateResolver
    }

    @Bean
    fun templateEngine(): SpringTemplateEngine {
        val springTemplateEngine = SpringTemplateEngine()
        springTemplateEngine.addTemplateResolver(templateResolver())
        return springTemplateEngine
    }

    @Bean
    fun viewResolver(): ThymeleafViewResolver {
        val viewResolver = ThymeleafViewResolver()
        viewResolver.templateEngine = templateEngine()
        viewResolver.order = 0
        return viewResolver
    }
}
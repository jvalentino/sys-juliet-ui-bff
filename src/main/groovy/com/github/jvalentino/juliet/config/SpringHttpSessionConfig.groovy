package com.github.jvalentino.juliet.config

import groovy.transform.CompileDynamic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.MapSessionRepository
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession

import java.util.concurrent.ConcurrentHashMap

/**
 * The only way for session to work in memory is to use this
 */
@EnableSpringHttpSession
@Configuration
@CompileDynamic
class SpringHttpSessionConfig {

    @Bean
    MapSessionRepository sessionRepository() {
        new MapSessionRepository(new ConcurrentHashMap<>())
    }

}

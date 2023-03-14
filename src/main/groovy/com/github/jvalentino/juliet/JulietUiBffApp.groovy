package com.github.jvalentino.juliet

import groovy.transform.CompileDynamic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Main application
 */
@SpringBootApplication
@CompileDynamic
class JulietUiBffApp {

    static void main(String[] args) {
        SpringApplication.run(JulietUiBffApp, args)
    }

}

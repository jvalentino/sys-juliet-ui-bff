package com.github.jvalentino.juliet.rest

import com.github.jvalentino.juliet.dto.DashboardDto
import com.github.jvalentino.juliet.service.BffService
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Generic services for the BFF
 */
@CompileDynamic
@Slf4j
@RestController
class BffRest {

    @Autowired
    BffService bffService

    @GetMapping('/dashboard')
    @CircuitBreaker(name = 'Dashboard')
    DashboardDto dashboard() {
        DashboardDto result = new DashboardDto()
        result.with {
            documents = bffService.allDocs()
        }
        result
    }

}

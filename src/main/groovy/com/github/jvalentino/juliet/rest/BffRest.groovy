package com.github.jvalentino.juliet.rest

import com.github.jvalentino.juliet.doc.api.DocRestApi
import com.github.jvalentino.juliet.doc.model.DocListDto
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Generic services for the BFF
 */
@CompileDynamic
@Slf4j
@RestController
class BffRest {

    @GetMapping('/')
    @CircuitBreaker(name = 'Index')
    DocListDto index() {
        DocRestApi api = new DocRestApi()
        api.apiClient.apiKey = '123'
        DocListDto result = api.dashboard()
        result
    }

}

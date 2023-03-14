package com.github.jvalentino.juliet.rest

import com.github.jvalentino.juliet.dto.DashboardDto
import com.github.jvalentino.juliet.dto.HomeDto
import com.github.jvalentino.juliet.dto.LoginDto
import com.github.jvalentino.juliet.service.AuthService
import com.github.jvalentino.juliet.service.BffService
import com.github.jvalentino.juliet.user.model.UserDto
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpSession

/**
 * Generic services for the BFF
 */
@CompileDynamic
@Slf4j
@RestController
class BffRest {

    @Autowired
    BffService bffService

    @Autowired
    AuthService authService

    @Autowired
    AuthenticationManager authenticationManager

    @GetMapping('/')
    @CircuitBreaker(name = 'Index')
    HomeDto index() {
        bffService.index()
    }

    @GetMapping('/dashboard')
    @CircuitBreaker(name = 'Dashboard')
    DashboardDto dashboard() {
        DashboardDto result = new DashboardDto()
        result.with {
            documents = bffService.allDocs()
        }
        result
    }

    @PostMapping('/custom-login')
    LoginDto login(@RequestBody UserDto user, HttpSession session) {
        LoginDto result = authService.login(user, authenticationManager, session)
        result
    }

}

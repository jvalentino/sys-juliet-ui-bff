package com.github.jvalentino.juliet.config

import com.github.jvalentino.juliet.service.AuthService
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.session.web.http.HeaderHttpSessionIdResolver
import org.springframework.session.web.http.HttpSessionIdResolver

/**
 * Another magical class that handles both allowing access of endpoints without auth,
 * and that also established a custom auth manager.
 * @author john.valentino
 */
@Configuration
@EnableWebSecurity
@Slf4j
@CompileDynamic
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthService authService

    // https://stackoverflow.com/questions/4664893/
    // //how-to-manually-set-an-authenticated-user-in-spring-security-springmvc
    @Bean
    AuthenticationManager customAuthenticationManager() throws Exception {
        new AuthenticationManager() {

            @Override
            Authentication authenticate(Authentication authentication) throws AuthenticationException {
                authService.authenticate(authentication)
            }

        }
    }

    @Bean
    HttpSessionIdResolver httpSessionIdResolver() {
        HeaderHttpSessionIdResolver.xAuthToken()
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // https://stackoverflow.com/questions/32064000/uploading-file-returns-403-error-spring-mvc
        http.cors().and().csrf().disable()

        http
                .authorizeRequests()
                .antMatchers(
                        '/resources/**',
                        '/webjars/**',
                        '/',
                        '/custom-login',
                        '/invalid',
                        '/actuator/prometheus',
                        '/actuator/health',
                        '/v3/**',
                        '/swagger-ui/**',
                ).permitAll()
                .anyRequest().authenticated()
    }

}

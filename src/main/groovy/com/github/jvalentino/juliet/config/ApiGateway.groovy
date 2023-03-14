package com.github.jvalentino.juliet.config

import com.github.jvalentino.juliet.doc.api.DocRestApi
import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * General API access
 * @author john.valentino
 */
@CompileDynamic
@Configuration
class ApiGateway {

    @Value('${management.apiKeyDoc}')
    String apiKeyDoc

    @Value('${management.apiKeyUser}')
    String apiKeyUser

    @Bean
    DocRestApi docRestApi() {
        DocRestApi api = new DocRestApi()
        api.apiClient.apiKey = apiKeyDoc
        api
    }

}

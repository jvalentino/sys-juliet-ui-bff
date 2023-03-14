package com.github.jvalentino.juliet


import org.springframework.boot.SpringApplication
import spock.lang.Specification

class JulietUiBffAppTest extends Specification {

    def setup() {
        GroovyMock(SpringApplication, global:true)
    }

    def "test main"() {
        when:
        JulietUiBffApp.main(null)

        then:
        1 * SpringApplication.run(JulietUiBffApp, null)
    }

}

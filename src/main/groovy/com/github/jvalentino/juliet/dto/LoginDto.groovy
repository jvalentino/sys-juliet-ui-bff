package com.github.jvalentino.juliet.dto

import groovy.transform.CompileDynamic

/**
 * Response to login
 */
@CompileDynamic
class LoginDto extends ResultDto {

    String sessionId
    String sessionIdBase64

}

package com.jcminarro.authexample.config.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class InvalidCredentialException: RuntimeException()
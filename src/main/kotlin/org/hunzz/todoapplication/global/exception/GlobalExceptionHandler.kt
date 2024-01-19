package org.hunzz.todoapplication.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    fun handleModelNotFoundException(e: ModelNotFoundException) =
        ResponseEntity.badRequest().body(e.message)

    fun handleInvalidCriteriaException(e: InvalidCriteriaException) =
        ResponseEntity.badRequest().body(e.message)

    fun handleInvalidPasswordException(e: InvalidPasswordException) =
        ResponseEntity.badRequest().body(e.message)

    fun handleInvalidCredentialException(e: InvalidCredentialException) =
        ResponseEntity.badRequest().body(e.message)
}
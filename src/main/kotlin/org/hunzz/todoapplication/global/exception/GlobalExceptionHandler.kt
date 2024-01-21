package org.hunzz.todoapplication.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException) =
        ResponseEntity.badRequest().body(e.message)

    @ExceptionHandler(InvalidCriteriaException::class)
    fun handleInvalidCriteriaException(e: InvalidCriteriaException) =
        ResponseEntity.badRequest().body(e.message)

    @ExceptionHandler(InvalidPasswordException::class)
    fun handleInvalidPasswordException(e: InvalidPasswordException) =
        ResponseEntity.badRequest().body(e.message)

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredentialException(e: InvalidCredentialException) =
        ResponseEntity.badRequest().body(e.message)

    @ExceptionHandler(InvalidJwtException::class)
    fun handleInvalidAccessException(e: InvalidJwtException) =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
}
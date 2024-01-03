package org.example.todoapplication.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: Exception) = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: Exception) = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)

    @ExceptionHandler(ExceedMaxLengthException::class)
    fun handleExceedMaxLengthException(e: Exception) = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
}
package org.hunzz.todoapplication.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    fun handleModelNotFoundException(e: ModelNotFoundException) =
        ResponseEntity.badRequest().body(e.message)

    fun handleWrongCriteriaException(e: WrongCriteriaException) =
        ResponseEntity.badRequest().body(e.message)
}
package org.hunzz.todoapplication.global.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                httpStatus = "401 Bad Request",
                message = e.bindingResult.allErrors.toMutableList().first().defaultMessage!!,
                path = request.requestURI.toString(),
                errors = e.bindingResult.allErrors.toMutableList().first().let {
                    it as FieldError
                    it.field
                }
            )
        )
}
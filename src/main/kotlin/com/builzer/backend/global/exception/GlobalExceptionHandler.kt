package com.builzer.backend.global.exception

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.logger
import org.apache.coyote.BadRequestException
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Order(9999)
@RestControllerAdvice
class GlobalExceptionHandler {

    val log = logger()

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ApiResponse<String> {
        return e.toApiResponse()
    }

    fun MethodArgumentNotValidException.toApiResponse(): ApiResponse<String> {
        val message = this.bindingResult.fieldError?.defaultMessage ?: this.message
        return ApiResponse.badRequest(message)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException): ApiResponse<String> {
        return e.toApiResponse()
    }

    fun BadRequestException.toApiResponse(): ApiResponse<String> {
        val message = this.message ?: "Bad Request"
        return ApiResponse.badRequest(message)
    }

    @ExceptionHandler(Throwable::class)
    fun handleGlobalException(e: Throwable): ApiResponse<String> {
        log.error(e.stackTraceToString())
        return ApiResponse.of(
            code = HttpStatus.INTERNAL_SERVER_ERROR,
            data = "Internal Server Error"
        )
    }

}
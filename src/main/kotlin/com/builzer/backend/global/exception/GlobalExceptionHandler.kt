package com.builzer.backend.global.exception

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.logger
import feign.FeignException
import org.apache.coyote.BadRequestException
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(9999)
@RestControllerAdvice
class GlobalExceptionHandler {

    val log = logger()

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ApiResponse<String> {
        return e.toApiResponse()
    }

    fun MethodArgumentNotValidException.toApiResponse(): ApiResponse<String> {
        val message = this.bindingResult.fieldError?.defaultMessage ?: this.message
        return ApiResponse.badRequest(message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e: BadRequestException): ApiResponse<String> {
        return e.toApiResponse()
    }

    fun BadRequestException.toApiResponse(): ApiResponse<String> {
        val message = this.message ?: "Bad Request"
        return ApiResponse.badRequest(message)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable::class)
    fun handleGlobalException(e: Throwable): ApiResponse<String> {
        log.error(e.stackTraceToString())
        return ApiResponse.of(
            code = HttpStatus.INTERNAL_SERVER_ERROR,
            data = "Internal Server Error"
        )
    }

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(e: FeignException): ApiResponse<String> {
        return e.toApiResponse()
    }

    fun FeignException.toApiResponse(): ApiResponse<String> {
        val message = this.message ?: "External API Error"
        val statusCode = this.status()
        return ApiResponse.of(
            code = HttpStatus.valueOf(statusCode),
            data = message
        )
    }

}
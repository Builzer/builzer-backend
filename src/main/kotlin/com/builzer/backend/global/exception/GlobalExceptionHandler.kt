package com.builzer.backend.global.exception

import com.builzer.backend.global.response.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Throwable::class)
    fun handleGlobalException(e: Throwable): ApiResponse<String> {
        return ApiResponse.of(
            code = HttpStatus.INTERNAL_SERVER_ERROR,
            data = "Internal Server Error"
        )
    }
    
}
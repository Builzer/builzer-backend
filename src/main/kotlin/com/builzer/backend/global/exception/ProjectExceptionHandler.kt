package com.builzer.backend.global.exception

import com.amazonaws.services.route53.model.ConflictingDomainExistsException
import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.logger
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(0)
@RestControllerAdvice
class ProjectExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictingDomainExistsException::class)
    fun handleConflictingDomainExistsException(e: ConflictingDomainExistsException): ApiResponse<String> {
        return e.toApiResponse()
    }

    fun ConflictingDomainExistsException.toApiResponse(): ApiResponse<String> {
        val message = this.errorMessage ?: "Conflict Request"
        return ApiResponse.conflict(message)
    }

}
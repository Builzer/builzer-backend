package com.builzer.backend.global.response

import org.springframework.http.HttpStatus

class ApiResponse<T> private constructor(
    val code: Int = HttpStatus.OK.value(),
    val message: String = "",
    val data: T? = null
) {
    companion object {
        fun <T> ok(data: T): ApiResponse<T> {
            return ApiResponse(
                code = HttpStatus.OK.value(),
                message = HttpStatus.OK.reasonPhrase,
                data = data
            )
        }

        fun <T> created(data: T): ApiResponse<T> {
            return ApiResponse(
                code = HttpStatus.CREATED.value(),
                message = HttpStatus.CREATED.reasonPhrase,
                data = data
            )
        }

        fun <T> badRequest(data: T): ApiResponse<T> {
            return ApiResponse(
                code = HttpStatus.BAD_REQUEST.value(),
                message = HttpStatus.BAD_REQUEST.reasonPhrase,
                data = data
            )
        }

        fun <T> notFound(data: T): ApiResponse<T> {
            return ApiResponse(
                code = HttpStatus.NOT_FOUND.value(),
                message = HttpStatus.NOT_FOUND.reasonPhrase,
                data = data
            )
        }

        fun <T> of(data: T, code: HttpStatus): ApiResponse<T> {
            return ApiResponse(
                code = code.value(),
                message = code.reasonPhrase,
                data = data
            )
        }
    }
}
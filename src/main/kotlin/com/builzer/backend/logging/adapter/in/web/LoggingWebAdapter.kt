package com.builzer.backend.logging.adapter.`in`.web

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.logging.application.port.`in`.LoggingUseCase
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/logging")
class LoggingWebAdapter(
    private val loggingUseCase: LoggingUseCase
)
{
    @GetMapping("/projects/{projectId}/logs/download")
    fun getUrlWithLogDown(
        @PathVariable projectId: Long,
        @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().toString()}")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        date: LocalDate
    ): ApiResponse<String> {

        val response = loggingUseCase.getUrlForLogDown(projectId, date)
        return ApiResponse.ok(response)
    }
}
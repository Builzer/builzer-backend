package com.builzer.backend.logging.adapter.`in`.web

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.logging.application.port.`in`.LoggingUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/logging")
class LoggingWebAdapter(private val loggingUseCase: LoggingUseCase) {
    @GetMapping("/projects/{projectId}/logs/download")
    fun downloadLog(
        @PathVariable projectId: Long,
        @RequestParam(defaultValue = "#{T(java.time.LocalDate).now().toString()}")
        date: String
    ): ApiResponse<String> {

        val response = loggingUseCase.downloadMemberLog(projectId, date)
        return ApiResponse.ok(response)
    }
}
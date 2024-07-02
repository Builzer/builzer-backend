package com.builzer.backend.logging.application.port.`in`

import java.time.LocalDate

interface LoggingUseCase {
    fun getUrlForLogDown(projectId: Long, date: LocalDate): String
}
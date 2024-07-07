package com.builzer.backend.logging.application.port.`in`

interface LoggingUseCase {
    fun downloadMemberLog(projectId: Long, date: String): String
}
package com.builzer.backend.logging.application.service

import com.builzer.backend.logging.application.port.`in`.LoggingUseCase
import com.builzer.backend.logging.application.port.out.LogPort
import org.springframework.stereotype.Service

@Service
class LoggingService(
    private val logPort: LogPort
): LoggingUseCase {

    override fun downloadMemberLog(projectId: Long, date: String): String {
        val key: String = generateS3Key(projectId, date)

        return logPort.downloadMemberLog(key)
    }

    private fun generateS3Key(projectId: Long, date: String): String {
        return "$projectId/$date.log"
    }

}
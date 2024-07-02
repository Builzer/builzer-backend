package com.builzer.backend.logging.application.service

import com.builzer.backend.logging.application.port.`in`.LoggingUseCase
import com.builzer.backend.logging.application.port.out.S3Port
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDate

@Service
class LoggingService(
    private val s3Port: S3Port
): LoggingUseCase {

    override fun getUrlForLogDown(projectId: Long, date: LocalDate): String {
        val timestamp: Timestamp = Timestamp.valueOf(date.atStartOfDay())
        val key: String = generateKey(projectId, timestamp)

        return s3Port.getPreSignedUrl(key);
    }

    private fun generateKey(projectId: Long, timestamp: Timestamp): String {
        return "$projectId/${timestamp.toLocalDateTime().toLocalDate()}.log"
    }

}
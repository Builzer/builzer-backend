package com.builzer.backend.logging.application.port.out

interface LogPort {
    fun downloadMemberLog(key: String): String
}
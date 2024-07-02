package com.builzer.backend.logging.application.port.out

interface S3Port {
    fun getPreSignedUrl(key: String): String
}
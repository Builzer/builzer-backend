package com.builzer.backend.global.utils

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class S3Utils(private val s3Client: AmazonS3) {

    fun getPreSignedUrl(bucket: String, key: String, method: HttpMethod, expirePeriod: Long): String {
        val expiration = Date()
        expiration.time += expirePeriod
        val generatePreSignedUrlRequest: GeneratePresignedUrlRequest = GeneratePresignedUrlRequest(bucket, key)
            .withMethod(method)
            .withExpiration(expiration)

        return s3Client.generatePresignedUrl(generatePreSignedUrlRequest).toString()
    }

}
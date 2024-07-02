package com.builzer.backend.logging.adapter.out.s3

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.builzer.backend.logging.application.port.out.S3Port
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class S3Adaptor(private val s3Client: AmazonS3) :S3Port {

    @Value("\${aws.s3.memberLogBucket}")
    lateinit var memberLogBucket: String

    companion object {
        const val EXPIRE_PERIOD = 60 * 15 * 1000L
    }

    override fun getPreSignedUrl(key: String): String {
        val expiration = Date()
        expiration.time += EXPIRE_PERIOD

        val generatePreSignedUrlRequest: GeneratePresignedUrlRequest = GeneratePresignedUrlRequest(memberLogBucket, key)
            .withMethod(HttpMethod.GET)
            .withExpiration(expiration)

        return s3Client.generatePresignedUrl(generatePreSignedUrlRequest).toString()
    }


}
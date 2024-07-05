package com.builzer.backend.logging.adapter.out.storage

import com.amazonaws.HttpMethod
import com.builzer.backend.global.utils.S3Utils
import com.builzer.backend.logging.application.port.out.LogPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class S3LogAdaptor(private val s3Utils: S3Utils) : LogPort {

    @Value("\${aws.s3.memberLogBucket}")
    lateinit var memberLogBucket: String

    companion object {
        const val MEMBER_LOG_EXPIRE_TIME_MS = 60 * 15 * 1000L
    }

    override fun downloadMemberLog(key: String): String {
        return s3Utils.getPreSignedUrl(memberLogBucket, key, HttpMethod.GET, MEMBER_LOG_EXPIRE_TIME_MS)
    }


}
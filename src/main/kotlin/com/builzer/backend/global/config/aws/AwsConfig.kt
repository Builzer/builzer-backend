package com.builzer.backend.global.config.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.route53.AmazonRoute53
import com.amazonaws.services.route53.AmazonRoute53ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig {

    @Value("\${aws.accessKeyId}")
    lateinit var accessKeyId: String

    @Value("\${aws.secretAccessKey}")
    lateinit var secretAccessKey: String

    @Value("\${aws.region}")
    lateinit var region: String

    @Bean
    fun route53Client(): AmazonRoute53 {
        val credentials = BasicAWSCredentials(accessKeyId, secretAccessKey)

        return AmazonRoute53ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .build()
    }
}
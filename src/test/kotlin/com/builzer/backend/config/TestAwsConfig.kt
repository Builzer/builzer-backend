package com.builzer.backend.config

import com.amazonaws.services.route53.AmazonRoute53
import io.mockk.mockk
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("test")
@Configuration
class TestAwsConfig {

    @Bean
    fun testRoute53Client(): AmazonRoute53 {
        return mockk<AmazonRoute53>()
    }
}
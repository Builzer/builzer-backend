package com.builzer.backend.global.config.feign;

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("!test")
@Configuration
class GithubOAuthClientConfig {
    @Bean
    fun unauthorizedHeaderRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor { request ->
            request.header("Accept", "application/vnd.github.v3+json")
            request.header("X-GitHub-Api-Version", "2022-11-28")
        }
    }
}

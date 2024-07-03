package com.builzer.backend.global.config.feign

import com.builzer.backend.global.config.security.MemberPayload
import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder

@Profile("!test")
@Configuration
class GithubClientConfig {
    @Bean
    fun headerRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor { request ->
            request.header("Accept", "application/vnd.github.v3+json")
            request.header("X-GitHub-Api-Version", "2022-11-28")

            val authentication = SecurityContextHolder.getContext().authentication

            if (authentication != null) {
                val memberPayload: MemberPayload = authentication.principal as MemberPayload

                request.header(HttpHeaders.AUTHORIZATION, "Bearer ${memberPayload.gitAccessToken}")
            }
        }
    }
}
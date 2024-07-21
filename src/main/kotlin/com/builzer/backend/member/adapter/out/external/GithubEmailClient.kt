package com.builzer.backend.member.adapter.out.external

import com.builzer.backend.global.config.feign.GithubOAuthClientConfig
import com.builzer.backend.member.adapter.out.external.response.UserEmail
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "GithubEmailClient",
    url = "\${feign.endpoint.github.api}",
    configuration = [GithubOAuthClientConfig::class]
)
interface GithubEmailClient {
    @GetMapping("/user/emails")
    fun getUserEmails(@RequestHeader headers: HttpHeaders): List<UserEmail>
}
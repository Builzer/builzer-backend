package com.builzer.backend.member.adapter.out.external

import com.builzer.backend.global.config.feign.GithubClientConfig
import com.builzer.backend.member.application.port.`in`.response.UserInfoFromGithub
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "GithubMemberClient",
    url = "https://api.github.com",
    configuration = [GithubClientConfig::class]
)
interface GithubMemberClient {
    @GetMapping("/user")
    fun getUser(@RequestHeader headers: HttpHeaders): UserInfoFromGithub
}
package com.builzer.backend.member.adapter.out.external

import com.builzer.backend.global.config.feign.GithubOAuthClientConfig
import com.builzer.backend.member.adapter.out.external.request.OAuthExternalRequest
import com.builzer.backend.member.adapter.out.external.response.OAuthExternalResponse
import com.builzer.backend.member.adapter.out.external.response.UserEmail
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "GithubOAuthClient",
    url = "\${feign.endpoint.github.oauth}",
    configuration = [GithubOAuthClientConfig::class]
)
interface GithubOAuthClient {
    @PostMapping("/login/oauth/access_token")
    fun getAccessToken(@RequestBody oauthExternalRequest: OAuthExternalRequest): OAuthExternalResponse

    @GetMapping("/user/emails")
    fun getUserEmails(@RequestHeader headers: HttpHeaders): List<UserEmail>
}
package com.builzer.backend.member.adapter.out.external

import com.builzer.backend.global.config.feign.GithubClientConfig
import com.builzer.backend.member.adapter.out.external.request.OAuthExternalRequest
import com.builzer.backend.member.adapter.out.external.response.OAuthExternalResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "GithubOAuthClient",
    url = "\${feign.endpoint.github.oauth}",
    configuration = [GithubClientConfig::class]
)
interface GithubOAuthClient {
    @PostMapping("/login/oauth/access_token")
    fun getAccessToken(@RequestBody oauthExternalRequest: OAuthExternalRequest): OAuthExternalResponse
}
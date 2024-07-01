package com.builzer.backend.member.adapter.out.external

import com.builzer.backend.member.adapter.out.external.request.OAuthExternalRequest
import com.builzer.backend.member.application.port.`in`.response.UserInfoFromGithub
import com.builzer.backend.member.application.port.out.OAuthExternalRequestPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class OAuthExternalAdapter(
    private val githubOAuthClient: GithubOAuthClient,
    private val githubMemberClient: GithubMemberClient,
) : OAuthExternalRequestPort {
    @Value("\${oauth.github.client-id}")
    private lateinit var clientId: String

    @Value("\${oauth.github.client-secret}")
    private lateinit var clientSecret: String

    @Value("\${oauth.github.callback-uri}")
    private lateinit var callbackUri: String

    override fun requestGithubAccessToken(code: String): String {
        val accessTokenRequest = OAuthExternalRequest(
            clientId = clientId,
            clientSecret = clientSecret,
            code = code,
            redirectUri = callbackUri
        )

        val accessTokenResponse = githubOAuthClient.getAccessToken(accessTokenRequest)

        return accessTokenResponse.accessToken
    }

    override fun requestUserInfo(githubAccessToken: String): UserInfoFromGithub {
        val authorizationHeader = HttpHeaders()
        authorizationHeader.setBearerAuth(githubAccessToken)

        val userInfoFromGithub = githubMemberClient.getUser(authorizationHeader)

        return userInfoFromGithub
    }
}
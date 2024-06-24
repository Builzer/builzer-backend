package com.builzer.backend.member.adapter.out.external

import com.builzer.backend.member.application.port.out.OAuthExternalRequestPort
import org.springframework.stereotype.Component

@Component
class OAuthExternalAdapter() : OAuthExternalRequestPort {
    override fun requestGithubAccessToken(code: String): String {
        TODO("request github access token with client_id, client_secret, code")
    }

    override fun requestUserInfo(githubAccessToken: String): String {
        TODO("request github user info with github access token")
    }
}
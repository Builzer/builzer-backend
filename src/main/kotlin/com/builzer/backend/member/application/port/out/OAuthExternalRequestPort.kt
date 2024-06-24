package com.builzer.backend.member.application.port.out

interface OAuthExternalRequestPort {
    fun requestUserInfo(githubAccessToken: String): String
    fun requestGithubAccessToken(code: String): String
}
package com.builzer.backend.member.application.port.out

import com.builzer.backend.member.application.port.`in`.response.UserInfoFromGithub

interface OAuthExternalRequestPort {
    fun requestUserInfo(githubAccessToken: String): UserInfoFromGithub
    fun requestGithubAccessToken(code: String): String
}
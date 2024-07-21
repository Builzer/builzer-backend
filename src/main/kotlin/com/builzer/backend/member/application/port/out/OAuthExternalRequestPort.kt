package com.builzer.backend.member.application.port.out

import com.builzer.backend.member.adapter.out.external.response.UserEmail
import com.builzer.backend.member.application.port.`in`.result.UserInfoFromGithub

interface OAuthExternalRequestPort {
    fun requestUserInfo(githubAccessToken: String): UserInfoFromGithub
    fun requestGithubAccessToken(code: String): String
    fun requestUserEmails(githubAccessToken: String): List<UserEmail>
}
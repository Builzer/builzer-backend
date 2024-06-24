package com.builzer.backend.member.application.service

import com.builzer.backend.member.application.port.`in`.OAuthUseCase
import com.builzer.backend.member.application.port.`in`.command.OAuthCommand
import com.builzer.backend.member.application.port.`in`.response.OAuthResponse
import com.builzer.backend.member.application.port.out.MemberPort
import com.builzer.backend.member.application.port.out.OAuthExternalRequestPort
import org.springframework.stereotype.Service

@Service
class OAuthService(
    private val memberPort: MemberPort,
    private val oAuthExternalRequestPort: OAuthExternalRequestPort
) : OAuthUseCase {
    override fun signInOrSignUp(oAuthCommand: OAuthCommand): OAuthResponse {
        val githubAccessToken = oAuthExternalRequestPort.requestGithubAccessToken(oAuthCommand.code)

        val userInfo = oAuthExternalRequestPort.requestUserInfo(githubAccessToken)

        val member = memberPort.createOrRead()

        TODO("create token and set userinfo into security context")

    }
}
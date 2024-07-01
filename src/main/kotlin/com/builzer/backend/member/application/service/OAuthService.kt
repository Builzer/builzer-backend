package com.builzer.backend.member.application.service

import com.builzer.backend.global.util.JwtUtil
import com.builzer.backend.member.application.port.`in`.OAuthUseCase
import com.builzer.backend.member.application.port.`in`.command.CreateOrReadCommand
import com.builzer.backend.member.application.port.`in`.command.OAuthCommand
import com.builzer.backend.member.application.port.`in`.response.OAuthResponse
import com.builzer.backend.member.application.port.out.MemberPort
import com.builzer.backend.member.application.port.out.OAuthExternalRequestPort
import org.springframework.stereotype.Service

@Service
class OAuthService(
    private val memberPort: MemberPort,
    private val oAuthExternalRequestPort: OAuthExternalRequestPort,
    private val jwtUtil: JwtUtil
) : OAuthUseCase {
    override fun signInOrSignUp(oAuthCommand: OAuthCommand): OAuthResponse {
        // retrieve access token from GitHub
        val githubAccessToken = oAuthExternalRequestPort.requestGithubAccessToken(oAuthCommand.code)

        // retrieve user info from GitHub with access token
        val userInfoFromGithub = oAuthExternalRequestPort.requestUserInfo(githubAccessToken)

        val createOrReadCommand = CreateOrReadCommand(
            githubAccessToken = githubAccessToken,
            name = userInfoFromGithub.name,
            email = userInfoFromGithub.email
        )

        // get member info
        val member = memberPort.createOrRead(createOrReadCommand)

        // generate access token
        val accessToken =
            jwtUtil.generateAccessToken(
                email = member.gitEmail,
                memberId = member.id,
                gitAccessToken = member.gitAccessToken
            )

        // generate refresh token
        val refreshToken =
            jwtUtil.generateRefreshToken(
                email = member.gitEmail,
                memberId = member.id,
                gitAccessToken = member.gitAccessToken
            )

        return OAuthResponse(
            member = member,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
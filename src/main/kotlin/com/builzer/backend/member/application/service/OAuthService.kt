package com.builzer.backend.member.application.service

import com.builzer.backend.global.util.JwtUtil
import com.builzer.backend.member.application.port.`in`.OAuthUseCase
import com.builzer.backend.member.application.port.`in`.command.CreateOrReadMemberCommand
import com.builzer.backend.member.application.port.`in`.command.OAuthCommand
import com.builzer.backend.member.application.port.`in`.result.OAuthResult
import com.builzer.backend.member.application.port.out.MemberPort
import com.builzer.backend.member.application.port.out.OAuthExternalRequestPort
import org.springframework.stereotype.Service

@Service
class OAuthService(
    private val memberPort: MemberPort,
    private val oAuthExternalRequestPort: OAuthExternalRequestPort,
    private val jwtUtil: JwtUtil
) : OAuthUseCase {
    override fun signInOrSignUp(oAuthCommand: OAuthCommand): OAuthResult {
        // retrieve access token from GitHub
        val githubAccessToken = oAuthExternalRequestPort.requestGithubAccessToken(oAuthCommand.code)

        // retrieve user info from GitHub with access token
        val userInfoFromGithub = oAuthExternalRequestPort.requestUserInfo(githubAccessToken)

        if (userInfoFromGithub.email == null) {
            userInfoFromGithub.email =
                oAuthExternalRequestPort.requestUserEmails(githubAccessToken)
                    .find { userEmail -> userEmail.primary }?.email
        }

        val createOrReadMemberCommand = CreateOrReadMemberCommand(
            githubAccessToken = githubAccessToken,
            name = userInfoFromGithub.name,
            email = userInfoFromGithub.email!!
        )

        // get member info
        val member = memberPort.createOrRead(createOrReadMemberCommand)

        // generate access token
        val accessToken =
            jwtUtil.generateAccessToken(
                email = member.gitEmail,
                memberId = member.id,
                gitAccessToken = member.gitAccessToken,
                gitNickname = member.name
            )

        // generate refresh token
        val refreshToken =
            jwtUtil.generateRefreshToken(
                email = member.gitEmail,
                memberId = member.id,
                gitAccessToken = member.gitAccessToken,
                gitNickname = member.name
            )

        return OAuthResult(
            member = member,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
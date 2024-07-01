package com.builzer.backend.member.adapter.`in`.web

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.member.adapter.`in`.web.request.OAuthRequest
import com.builzer.backend.member.adapter.`in`.web.response.OAuthResponse
import com.builzer.backend.member.application.port.`in`.OAuthUseCase
import com.builzer.backend.member.util.MemberMapper
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberWebAdapter(
    private val oAuthUseCase: OAuthUseCase
) {
    companion object {
        private val mapper = Mappers.getMapper(MemberMapper::class.java)
    }

    @PostMapping("/oauth/github")
    fun oauthGithubUser(
        @Valid @RequestBody request: OAuthRequest,
        httpResponse: HttpServletResponse
    ): ApiResponse<OAuthResponse> {
        val command = mapper.requestToCommand(request)
        val response = oAuthUseCase.signInOrSignUp(command)

        val oauthResponse = OAuthResponse(
            gitEmail = response.member.gitEmail,
            profileImg = response.member.profileImg,
            name = response.member.name,
            totalCredit = response.member.totalCredit,
            isInvited = response.member.isInvited
        )

        httpResponse.setHeader("Access-Token", response.accessToken)
        httpResponse.setHeader("Refresh-Token", response.refreshToken)
        return ApiResponse.ok(oauthResponse)
    }
}
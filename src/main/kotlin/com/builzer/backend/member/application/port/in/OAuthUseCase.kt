package com.builzer.backend.member.application.port.`in`

import com.builzer.backend.member.application.port.`in`.command.OAuthCommand
import com.builzer.backend.member.application.port.`in`.response.OAuthResponse

interface OAuthUseCase {
    fun signInOrSignUp(oAuthCommand: OAuthCommand): OAuthResponse
}
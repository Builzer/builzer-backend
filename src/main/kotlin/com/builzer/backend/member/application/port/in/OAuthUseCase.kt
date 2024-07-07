package com.builzer.backend.member.application.port.`in`

import com.builzer.backend.member.application.port.`in`.command.OAuthCommand
import com.builzer.backend.member.application.port.`in`.result.OAuthResult

interface OAuthUseCase {
    fun signInOrSignUp(oAuthCommand: OAuthCommand): OAuthResult
}
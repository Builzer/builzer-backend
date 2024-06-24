package com.builzer.backend.member.application.port.`in`.response

import com.builzer.backend.member.domain.Member

data class OAuthResponse(
    val member: Member,
    val accessToken: String,
    val refreshToken: String
)
package com.builzer.backend.member.application.port.`in`.result

import com.builzer.backend.member.domain.Member

data class OAuthResult(
    val member: Member,
    val accessToken: String,
    val refreshToken: String
)
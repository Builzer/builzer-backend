package com.builzer.backend.global.config.security

class MemberPayload(
    val id: Long,
    val email: String,
    val gitAccessToken: String
)
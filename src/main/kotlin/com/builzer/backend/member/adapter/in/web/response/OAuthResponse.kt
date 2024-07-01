package com.builzer.backend.member.adapter.`in`.web.response

data class OAuthResponse(
    val gitEmail: String,
    val profileImg: Int,
    val name: String,
    val totalCredit: Int,
    val isInvited: Boolean
)
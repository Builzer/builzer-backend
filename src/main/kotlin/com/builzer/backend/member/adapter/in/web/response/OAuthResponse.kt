package com.builzer.backend.member.adapter.`in`.web.response

import java.math.BigDecimal

data class OAuthResponse(
    val gitEmail: String,
    val profileImg: Int,
    val name: String,
    val totalCredit: BigDecimal,
    val isInvited: Boolean
)
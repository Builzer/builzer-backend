package com.builzer.backend.member.domain

import java.time.LocalDateTime

class Member(
    val id: Long,
    val profileImg: Int,
    val name: String,
    val gitEmail: String,
    val gitAccessToken: String,
    val lastLoginDate: LocalDateTime,
    val totalCredit: Int,
    val quitDate: LocalDateTime?,
    val customerKey: String,
    val billingKey: String?,
    val isInvited: Boolean
)
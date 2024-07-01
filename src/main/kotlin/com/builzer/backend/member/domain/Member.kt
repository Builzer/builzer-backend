package com.builzer.backend.member.domain

import java.sql.Timestamp

class Member(
    val id: Long,
    val profileImg: Int,
    val name: String,
    val gitEmail: String,
    val gitAccessToken: String,
    val lastLoginDate: Timestamp,
    val totalCredit: Int,
    val quitDate: Timestamp?,
    val customerKey: String,
    val billingKey: String?,
    val isInvited: Boolean = false
)
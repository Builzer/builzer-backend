package com.builzer.backend.member.adapter.out.external.response

data class UserEmail(
    val email: String,
    val verified: Boolean,
    val primary: Boolean,
    val visibility: String?
)
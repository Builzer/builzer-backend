package com.builzer.backend.member.adapter.`in`.web.request

import jakarta.validation.constraints.NotBlank

data class OAuthRequest(
    @NotBlank(message = "code cannot be empty")
    var code: String
)
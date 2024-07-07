package com.builzer.backend.member.application.port.`in`.result

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UserInfoFromGithub(
    val email: String,

    @JsonProperty("login")
    val name: String
)
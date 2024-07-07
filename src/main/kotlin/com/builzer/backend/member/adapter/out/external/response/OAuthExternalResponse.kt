package com.builzer.backend.member.adapter.out.external.response

import com.fasterxml.jackson.annotation.JsonProperty

class OAuthExternalResponse(
    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("scope")
    val scope: String,

    @JsonProperty("token_type")
    val tokenType: String,
)
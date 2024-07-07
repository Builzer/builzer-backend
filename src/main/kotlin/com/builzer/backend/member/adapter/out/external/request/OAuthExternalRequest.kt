package com.builzer.backend.member.adapter.out.external.request

import com.fasterxml.jackson.annotation.JsonProperty

class OAuthExternalRequest(
    @JsonProperty("client_id")
    val clientId: String,

    @JsonProperty("client_secret")
    val clientSecret: String,
    
    val code: String,

    @JsonProperty("redirect_uri")
    val redirectUri: String
)
package com.builzer.backend.project.adapter.out.client.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GithubRepoInfoResponse(
    @JsonProperty("full_name") val fullName: String,
    val private: Boolean,
    val language: String?,
    @JsonProperty("updated_at") val updatedAt: String?,
)
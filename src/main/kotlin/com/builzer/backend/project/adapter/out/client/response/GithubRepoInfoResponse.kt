package com.builzer.backend.project.adapter.out.client.response

data class GithubRepoInfoResponse(
    val full_name: String,
    val private: Boolean,
    val language: String?,
    val updated_at: String?,
)
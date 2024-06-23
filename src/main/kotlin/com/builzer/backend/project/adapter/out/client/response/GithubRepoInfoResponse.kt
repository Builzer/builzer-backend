package com.builzer.backend.project.adapter.out.client.response

import com.builzer.backend.project.adapter.`in`.web.response.RepoInfoResponse

class GithubRepoInfoResponse(
    val full_name: String,
    val private: Boolean,
    val updated_at: String,
)
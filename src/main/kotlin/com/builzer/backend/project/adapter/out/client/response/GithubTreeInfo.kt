package com.builzer.backend.project.adapter.out.client.response

data class GithubTreeInfo (
    val path: String,
    val sha: String,
    val type: String
)
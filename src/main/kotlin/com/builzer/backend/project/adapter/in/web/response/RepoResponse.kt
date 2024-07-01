package com.builzer.backend.project.adapter.`in`.web.response

data class RepoResponse (
    val repoName: String,
    val isPrivate: Boolean,
    val projectType: String,
    val updatedAt: String
)
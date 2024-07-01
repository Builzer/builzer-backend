package com.builzer.backend.project.adapter.`in`.web.response

import com.fasterxml.jackson.annotation.JsonIgnore

data class RepoTreeResponse (
    val label: String,
    val value: String,
    @JsonIgnore val path: String,
    val children: MutableList<RepoTreeResponse>
)
package com.builzer.backend.project.adapter.`in`.web.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BranchResponse(
    val branchName: String,
    @JsonProperty("value") val sha: String
)
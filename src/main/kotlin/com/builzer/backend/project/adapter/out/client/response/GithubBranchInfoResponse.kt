package com.builzer.backend.project.adapter.out.client.response

data class GithubBranchInfoResponse(
  val name: String,
  val commit: GithubCommitInfo
)
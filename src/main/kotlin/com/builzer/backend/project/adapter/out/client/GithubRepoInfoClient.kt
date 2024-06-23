package com.builzer.backend.project.adapter.out.client

import com.builzer.backend.project.adapter.out.client.response.GithubBranchInfoResponse
import com.builzer.backend.project.adapter.out.client.response.GithubItemInfoResponse
import com.builzer.backend.project.adapter.out.client.response.GithubRepoInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "GithubRepoInfoClient",
    url = "https://api.github.com"
)
interface GithubRepoInfoClient {
    @GetMapping("/user/repos")
    fun getRepoInfo(
        @RequestHeader("Authorization") gitToken: String,
        @RequestParam(required = false) affiliation: String? = null
    ): List<GithubRepoInfoResponse>

    @GetMapping("/repos/{repoName}/branches")
    fun getBranchInfo(
        @RequestHeader("Authorization") gitToken: String,
        @PathVariable(required = true) repoName: String
    ): List<GithubBranchInfoResponse>

    @GetMapping("/repos/{repoName}/contents/{path}")
    fun getItemInfo(
        @RequestHeader("Authorization") gitToken: String,
        @PathVariable(required = true) repoName: String,
        @PathVariable(required = false) path: String,
        @RequestParam(required = false) ref: String? = null
    ): List<GithubItemInfoResponse>
}
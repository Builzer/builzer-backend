package com.builzer.backend.project.adapter.out.client

import com.builzer.backend.project.adapter.out.client.response.*
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

    @GetMapping("/user/orgs")
    fun getOrgInfo(
        @RequestHeader("Authorization") gitToken: String
    ): List<GithubOrgInfoResponse>

    @GetMapping("/user/repos")
    fun getRepoInfo(
        @RequestHeader("Authorization") gitToken: String,
        @RequestParam(required = false) affiliation: String? = null
    ): List<GithubRepoInfoResponse>

    @GetMapping("/orgs/{orgName}/repos")
    fun getOrgRepoInfo(
        @RequestHeader("Authorization") gitToken: String,
        @PathVariable("orgName") orgName: String
    ): List<GithubRepoInfoResponse>

    @GetMapping("/repos/{owner}/{repoName}/branches")
    fun getBranchInfo(
        @RequestHeader("Authorization") gitToken: String,
        @PathVariable("owner") owner: String,
        @PathVariable(required = true) repoName: String
    ): List<GithubBranchInfoResponse>

    @GetMapping("/repos/{owner}/{repoName}/contents/{path}")
    fun getItemInfo(
        @RequestHeader("Authorization") gitToken: String,
        @PathVariable(required = true) owner: String,
        @PathVariable(required = true) repoName: String,
        @PathVariable(required = false) path: String,
        @RequestParam(required = false) ref: String? = null
    ): List<GithubItemInfoResponse>

    @GetMapping("/repos/{owner}/{repoName}/git/trees/{sha}")
    fun getTreeInfo(
        @RequestHeader("Authorization") gitToken: String,
        @PathVariable(required = true) owner: String,
        @PathVariable(required = true) repoName: String,
        @PathVariable(required = true) sha: String,
        @RequestParam(required = false) recursive: Int? = null,
    ): GithubTreeInfoResponse
}
package com.builzer.backend.project.adapter.`in`.web

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.OrgResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoItemListResponse
import com.builzer.backend.project.application.port.`in`.RepoInfoUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
class ProjectWebAdapter(
    private val repoInfoUseCase: RepoInfoUseCase
) {
    @GetMapping("/orgs")
    fun getOrganizationList(): ApiResponse<List<OrgResponse>> {
        val response = repoInfoUseCase.getOrgList()
        return ApiResponse.ok(response)
    }

    @GetMapping("/repos")
    fun getRepoList(
        @RequestParam(defaultValue = "owner") possession: String,
        @RequestParam(required = false) orgName: String?,
    ): ApiResponse<List<RepoResponse>> {
        val response = repoInfoUseCase.getRepoList(possession, orgName)
        return ApiResponse.ok(response)
    }

    @GetMapping("/{repoName}/branches")
    fun getBranchList(@PathVariable repoName: String): ApiResponse<List<BranchResponse>> {
        val response = repoInfoUseCase.getBranchList(repoName)
        return ApiResponse.ok(response)
    }

    @GetMapping("/{repoName}/folders")
    fun getRepoItemList(
        @PathVariable repoName: String,
        @RequestParam(defaultValue = "master") branchName: String,
        @RequestParam(defaultValue = "/") path: String
    ): ApiResponse<List<RepoItemListResponse>> {
        val response = repoInfoUseCase.getRepoItemList(repoName, branchName, path)
        return ApiResponse.ok(response)
    }

}
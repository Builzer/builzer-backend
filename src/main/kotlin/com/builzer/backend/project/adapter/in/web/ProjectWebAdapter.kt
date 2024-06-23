package com.builzer.backend.project.adapter.`in`.web

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoInfoResponse
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
    @GetMapping("/repos")
    fun getRepoList(): ApiResponse<List<RepoInfoResponse>> {
        // 토큰 획득 필요
        val token = ""
        val response = repoInfoUseCase.getRepoList(token)
        return ApiResponse.ok(response)
    }

    @GetMapping("/{repoName}/branches")
    fun getBranchList(@PathVariable repoName: String): ApiResponse<List<BranchResponse>> {
        // 토큰 획득 필요
        val token = ""
        val response = repoInfoUseCase.getBranchList(token, repoName)
        return ApiResponse.ok(response)
    }

    @GetMapping("/{repoName}/folders")
    fun getRepoItemList(
        @PathVariable repoName: String,
        @RequestParam(defaultValue = "master") branchName: String,
        @RequestParam(defaultValue = "/") path: String
    ): ApiResponse<List<RepoItemListResponse>> {
        // 토큰 획득 필요
        val token = ""
        val response = repoInfoUseCase.getRepoItemList(token, repoName, branchName, path)
        return ApiResponse.ok(response)
    }

}
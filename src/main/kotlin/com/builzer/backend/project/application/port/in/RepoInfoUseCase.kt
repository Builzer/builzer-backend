package com.builzer.backend.project.application.port.`in`

import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoItemListResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoInfoResponse

interface RepoInfoUseCase {
    fun getRepoList(token: String): List<RepoInfoResponse>
    fun getBranchList(token: String, repoName: String): List<BranchResponse>
    fun getRepoItemList(
        token: String,
        repoName: String,
        branch: String,
        path: String
    ): List<RepoItemListResponse>
}
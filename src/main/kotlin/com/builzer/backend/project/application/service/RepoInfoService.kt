package com.builzer.backend.project.application.service

import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoInfoResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoItemListResponse
import com.builzer.backend.project.application.port.`in`.RepoInfoUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RepoInfoService (
    @Value("\${github.request}") val requestUrl: String
): RepoInfoUseCase {
    override fun getRepoList(token: String): List<RepoInfoResponse> {
        TODO("Not yet implemented")
    }

    override fun getBranchList(token: String, repoName: String): List<BranchResponse> {
        TODO("Not yet implemented")
    }

    override fun getRepoItemList(
        token: String,
        repoName: String,
        branch: String,
        path: String
    ): List<RepoItemListResponse> {
        TODO("Not yet implemented")
    }
}
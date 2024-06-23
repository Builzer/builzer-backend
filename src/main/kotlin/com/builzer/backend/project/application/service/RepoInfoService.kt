package com.builzer.backend.project.application.service

import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoInfoResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoItemListResponse
import com.builzer.backend.project.adapter.out.client.GithubRepoInfoClient
import com.builzer.backend.project.adapter.out.client.mapper.GithubMapper
import com.builzer.backend.project.application.port.`in`.RepoInfoUseCase
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class RepoInfoService (
    private val githubRepoInfoClient: GithubRepoInfoClient
): RepoInfoUseCase {

    private val mapper = Mappers.getMapper(GithubMapper::class.java)

    override fun getRepoList(possession: String): List<RepoInfoResponse> {
        // To do 깃헙 토큰
        val gitToken = ""
        val githubRepoInfoResponse = githubRepoInfoClient.getRepoInfo(gitToken, possession)
        return mapper.toRepoInfo(githubRepoInfoResponse)
    }

    override fun getBranchList(repoName: String): List<BranchResponse> {
        // To do 깃헙 토큰
        val gitToken = ""
        val githubBranchResponse = githubRepoInfoClient.getBranchInfo(gitToken, repoName)
        return mapper.toBranchInfo(githubBranchResponse)
    }

    override fun getRepoItemList(
        repoName: String,
        branch: String,
        path: String
    ): List<RepoItemListResponse> {
        // To do 깃헙 토큰
        val gitToken = ""
        val githubItemInfoResponse = githubRepoInfoClient.getItemInfo(gitToken, repoName, path, branch)
        return mapper.toItemInfo(githubItemInfoResponse)
    }

}
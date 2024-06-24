package com.builzer.backend.project.application.port.`in`

import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.OrgResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoItemListResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoResponse

interface RepoInfoUseCase {
    fun getOrgList(): List<OrgResponse>
    fun getRepoList(
        possession: String,
        orgName: String?
    ): List<RepoResponse>

    fun getBranchList(repoName: String): List<BranchResponse>
    fun getRepoItemList(
        repoName: String,
        branch: String,
        path: String
    ): List<RepoItemListResponse>
}
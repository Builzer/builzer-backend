package com.builzer.backend.project.application.port.`in`

import com.builzer.backend.project.adapter.`in`.web.response.*

interface RepoInfoUseCase {
    fun getOrgList(): List<OrgResponse>
    fun getRepoList(
        possession: String,
        orgName: String?
    ): List<RepoResponse>

    fun getBranchList(owner: String, repoName: String): List<BranchResponse>
    fun getRepoItemList(
        owner: String,
        repoName: String,
        branch: String,
        path: String
    ): List<RepoItemListResponse>

    fun getRepoTreeList(
        owner: String,
        repoName: String,
        sha: String
    ): List<RepoTreeResponse>
}
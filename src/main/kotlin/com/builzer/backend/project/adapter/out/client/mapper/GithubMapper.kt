package com.builzer.backend.project.adapter.out.client.mapper

import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.OrgResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoItemListResponse
import com.builzer.backend.project.adapter.out.client.response.GithubBranchInfoResponse
import com.builzer.backend.project.adapter.out.client.response.GithubItemInfoResponse
import com.builzer.backend.project.adapter.out.client.response.GithubOrgInfoResponse
import com.builzer.backend.project.adapter.out.client.response.GithubRepoInfoResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface GithubMapper {
    @Mapping(source = "login", target = "orgName")
    fun toOrgInfo(githubOrgInfoResponse: List<GithubOrgInfoResponse>): List<OrgResponse>

    @Mappings(
        Mapping(source = "full_name", target = "repoName"),
        Mapping(source = "private", target = "isPrivate"),
        Mapping(source = "updated_at", target = "updatedAt"),
    )
    fun toRepoInfo(githubRepoInfoResponseList: List<GithubRepoInfoResponse>): List<RepoResponse>

    @Mapping(source = "name", target = "branchName")
    fun toBranchInfo(githubBranchInfoResponse: List<GithubBranchInfoResponse>): List<BranchResponse>

    @Mappings(
        Mapping(source = "name", target = "directoryName"),
        Mapping(source = "path", target = "path"),
    )
    fun toItemInfo(githubItemInfoResponse: List<GithubItemInfoResponse>): List<RepoItemListResponse>
}
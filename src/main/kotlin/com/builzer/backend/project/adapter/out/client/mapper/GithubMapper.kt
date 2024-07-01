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
import org.mapstruct.Named
import java.time.Duration
import java.time.ZonedDateTime

@Mapper
interface GithubMapper {

    @Mapping(source = "login", target = "orgName")
    fun toOrgInfo(githubOrgInfoResponse: GithubOrgInfoResponse): OrgResponse
    fun toOrgInfo(githubOrgInfoResponse: List<GithubOrgInfoResponse>): List<OrgResponse>

    @Mappings(
        Mapping(source = "full_name", target = "repoName"),
        Mapping(source = "private", target = "isPrivate"),
        Mapping(source = "language", target = "projectType", defaultValue = "None"),
        Mapping(source = "updated_at", target = "updatedAt", qualifiedByName = ["setUpdatedAt"])
    )
    fun toRepoInfo(githubRepoInfoResponse: GithubRepoInfoResponse): RepoResponse
    fun toRepoInfo(githubRepoInfoResponse: List<GithubRepoInfoResponse>): List<RepoResponse>

    @Mappings(
        Mapping(source = "name", target = "branchName"),
        Mapping(source = "commit.sha", target = "value")
    )
    fun toBranchInfo(githubBranchInfoResponse: GithubBranchInfoResponse): BranchResponse
    fun toBranchInfo(githubBranchInfoResponse: List<GithubBranchInfoResponse>): List<BranchResponse>

    @Mappings(
        Mapping(source = "name", target = "directoryName"),
        Mapping(source = "path", target = "path")
    )
    fun toItemInfo(githubItemInfoResponse: GithubItemInfoResponse): RepoItemListResponse
    fun toItemInfo(githubItemInfoResponse: List<GithubItemInfoResponse>): List<RepoItemListResponse>

    companion object {
        @JvmStatic
        @Named("setUpdatedAt")
        fun setUpdatedAt(updatedAt: String?): String {
            if (updatedAt == null) {
                return "None"
            }

            val updateTime = ZonedDateTime.parse(updatedAt)
            val duration = Duration.between(updateTime, ZonedDateTime.now())

            val days = duration.toDays()
            val hours = duration.toHours()
            val minutes = duration.toMinutes()
            val seconds = duration.toSeconds()

            return if (days > 365) {
                "more than a year ago"
            } else if (days > 30) {
                "more than a month ago"
            } else if (days > 0) {
                "$days days ago"
            } else if (hours > 0) {
                "$hours hours ago"
            } else if (minutes > 0) {
                "$minutes minutes ago"
            } else {
                "$seconds seconds ago"
            }
        }
    }
}
package com.builzer.backend.project.adapter.`in`.web

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.project.adapter.`in`.web.request.CheckDomainRequest
import com.builzer.backend.project.adapter.`in`.web.response.BranchResponse
import com.builzer.backend.project.adapter.`in`.web.response.OrgResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoResponse
import com.builzer.backend.project.adapter.`in`.web.response.RepoItemListResponse
import com.builzer.backend.project.application.port.`in`.ProjectDeployUseCase
import com.builzer.backend.project.application.port.`in`.RepoInfoUseCase
import com.builzer.backend.project.application.port.`in`.command.CheckDomainCommand
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/projects")
class ProjectWebAdapter(
        private val repoInfoUseCase: RepoInfoUseCase,
        private val projectDeployUseCase: ProjectDeployUseCase
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

    @GetMapping("/check-domain")
    fun checkDomain(
            @Valid @ModelAttribute checkDomainRequest: CheckDomainRequest): ApiResponse<String> {
        val checkDomainCommand = CheckDomainCommand.of(checkDomainRequest)
        projectDeployUseCase.checkDomainIsAvailable(checkDomainCommand = checkDomainCommand)
        return ApiResponse.ok("Available Domain URL")
    }

}
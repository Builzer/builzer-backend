package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectDetailJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildTool
import com.builzer.backend.project.adapter.out.persistence.entity.json.ProjectEnv

class ProjectDetail private constructor(
        val id: Long,
        val projectId: Long,
        val supportedLanguageId: Long,
        val supportedServerId: Long,
        val supportedDatabaseId: Long?,
        val gitRepositoryBranchName: String,
        val buildRootPath: String,
        val buildTool: BuildTool,
        val databaseUsername: String?,
        val databasePassword: String?,
        val isRunTest: Boolean,
        val env: List<ProjectEnv>) {

    companion object {

        fun of(projectDetailJpaEntity: ProjectDetailJpaEntity): ProjectDetail {
            return ProjectDetail(
                    id = projectDetailJpaEntity.id!!,
                    projectId = projectDetailJpaEntity.projectJpaEntity.id!!,
                    supportedLanguageId = projectDetailJpaEntity.supportedLanguageJpaEntity.id!!,
                    supportedServerId = projectDetailJpaEntity.supportedServerJpaEntity.id!!,
                    supportedDatabaseId = projectDetailJpaEntity.supportedDatabaseJpaEntity?.id!!,
                    gitRepositoryBranchName = projectDetailJpaEntity.gitRepositoryBranchName,
                    buildRootPath = projectDetailJpaEntity.buildRootPath,
                    buildTool = projectDetailJpaEntity.buildTool,
                    databaseUsername = projectDetailJpaEntity.databaseUsername,
                    databasePassword = projectDetailJpaEntity.databasePassword,
                    isRunTest = projectDetailJpaEntity.isRunTest,
                    env = projectDetailJpaEntity.env
            )
        }
    }
}
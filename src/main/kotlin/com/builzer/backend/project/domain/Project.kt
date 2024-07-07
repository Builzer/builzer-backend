package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectStatus

class Project private constructor(
        val id: Long,
        val projectPlanId: Long,
        val projectName: String,
        val projectStatus: ProjectStatus,
        val projectDomainName: String,
        val gitRepositoryName: String,
        val isPrivateGitRepository: Boolean) {

    companion object {

        fun of(projectJpaEntity: ProjectJpaEntity): Project {
            return Project(
                    id = projectJpaEntity.id!!,
                    projectPlanId = projectJpaEntity.projectPlanJpaEntity.id!!,
                    projectName = projectJpaEntity.projectName,
                    projectStatus = projectJpaEntity.projectStatus,
                    projectDomainName = projectJpaEntity.projectDomainName,
                    gitRepositoryName = projectJpaEntity.gitRepositoryName,
                    isPrivateGitRepository = projectJpaEntity.isPrivateGitRepository
            )
        }
    }
}
package com.builzer.backend.project.utils

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectDetailJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.ProjectJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.ProjectPlanJpaEntity
import com.builzer.backend.project.domain.Project
import com.builzer.backend.project.domain.ProjectDetail
import com.builzer.backend.project.domain.ProjectPlan
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface ProjectMapper {

    @Mappings(
        Mapping(source = "projectDetailList", target = "projectDetailList"),
        Mapping(source = "projectPlanJpaEntity", target = "projectPlan")
    )
    fun entityToDomain(projectJpaEntity: ProjectJpaEntity): Project
    fun entityToDomain(projectJpaEntity: List<ProjectJpaEntity>): List<Project>

    fun entityToDomain(projectDetailJpaEntity: ProjectDetailJpaEntity): ProjectDetail
    fun entityToDomain(projectDetailJpaEntity: List<ProjectDetailJpaEntity>): List<ProjectDetail>

    fun entityToDomain(projectPlanJpaEntity: ProjectPlanJpaEntity): ProjectPlan
    fun entityToDomain(projectPlanJpaEntity: List<ProjectPlanJpaEntity>): List<ProjectPlan>
}
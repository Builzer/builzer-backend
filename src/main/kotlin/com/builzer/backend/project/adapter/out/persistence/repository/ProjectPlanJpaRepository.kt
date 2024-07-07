package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectPlanJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectPlanType
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectPlanJpaRepository : JpaRepository<ProjectPlanJpaEntity, Long> {

    fun findByPlanType(planType: ProjectPlanType): ProjectPlanJpaEntity?
}
package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectPlanJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectPlanJpaRepository : JpaRepository<ProjectPlanJpaEntity, Long> {
}
package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectJpaRepository : JpaRepository<ProjectJpaEntity, Long> {
}
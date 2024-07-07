package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectDetailJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectDetailJpaRepository : JpaRepository<ProjectDetailJpaEntity, Long> {
}
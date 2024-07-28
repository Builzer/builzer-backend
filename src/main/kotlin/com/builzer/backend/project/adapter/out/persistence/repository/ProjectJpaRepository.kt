package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProjectJpaRepository : JpaRepository<ProjectJpaEntity, Long> {

    @Query("SELECT p FROM ProjectJpaEntity p JOIN ProjectMemberJpaEntity pm ON p.id = pm.projectJpaEntity.id WHERE pm.memberJpaEntity.id = :memberId AND pm.projectAuthority = 'OWNER'")
    fun findOwnerProjectsByMemberId(@Param("memberId") memberId: Long): List<ProjectJpaEntity>
}
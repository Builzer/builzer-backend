package com.builzer.backend.member.adapter.out.persistence.repository

import com.builzer.backend.member.adapter.out.persistence.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long> {
    fun findByGitEmail(gitEmail: String): MemberJpaEntity?
}
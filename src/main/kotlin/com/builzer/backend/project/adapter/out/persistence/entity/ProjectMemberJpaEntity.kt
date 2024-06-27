package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.member.adapter.out.persistence.entity.MemberJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectAuthority
import com.builzer.backend.project.adapter.out.persistence.entity.ids.ProjectMemberId
import jakarta.persistence.*

@Entity
@Table(name = "project_member")
@IdClass(ProjectMemberId::class)
class ProjectMemberJpaEntity(

        @Id
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id", nullable = false, updatable = false)
        val projectJpaEntity: ProjectJpaEntity,

        @Id
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id", nullable = false, updatable = false)
        val memberJpaEntity: MemberJpaEntity,

        @Enumerated(EnumType.STRING)
        @Column(name = "project_authority", nullable = false, updatable = false)
        val projectAuthority: ProjectAuthority

) : BaseTimeEntity()
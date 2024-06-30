package com.builzer.backend.project.adapter.out.persistence.entity.ids

import com.builzer.backend.member.adapter.out.persistence.entity.MemberJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.ProjectJpaEntity
import java.io.Serializable

data class ProjectMemberId(
        val memberJpaEntity: MemberJpaEntity,
        val projectJpaEntity: ProjectJpaEntity
) : Serializable
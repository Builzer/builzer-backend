package com.builzer.backend.project.domain

import com.builzer.backend.member.domain.Member
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectAuthority

class ProjectMember (
    val project: Project,
    val member: Member,
    val authority: ProjectAuthority
)
package com.builzer.backend.project.application.port.out

import com.builzer.backend.project.domain.Project

interface ProjectPort {
    fun findOwnerProjectsByMemberId(memberId: Long) : List<Project>
}
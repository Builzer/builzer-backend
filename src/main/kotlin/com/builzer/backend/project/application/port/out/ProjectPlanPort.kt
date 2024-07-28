package com.builzer.backend.project.application.port.out

import com.builzer.backend.project.domain.ProjectPlan

interface ProjectPlanPort {
    fun findAll(): List<ProjectPlan>
}
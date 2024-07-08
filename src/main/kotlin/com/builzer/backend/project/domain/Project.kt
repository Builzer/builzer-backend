package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectStatus
import java.sql.Timestamp

class Project(
    val id: Long,
    val projectPlan: ProjectPlan,
    val projectName: String,
    val projectStatus: ProjectStatus,
    val domain: String,
    val gitRepositoryName: String,
    val isPrivateRepository: Boolean,
    val lastDisabledAt: Timestamp?,
    val lastPaidAt: Timestamp?
)
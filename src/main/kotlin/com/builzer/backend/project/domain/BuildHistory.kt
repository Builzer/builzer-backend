package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildStatus

class BuildHistory(
    val id: Long,
    val project: Project,
    val projectDetail: ProjectDetail,
    val buildScript: String?,
    val runId: Long,
    val status: BuildStatus,
    val buildTime: Int,
    val executor: String,
    val lastCommit: String?,
    val commitInfo: String?
)

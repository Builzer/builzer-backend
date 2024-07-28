package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildTool
import com.builzer.backend.project.adapter.out.persistence.entity.json.ProjectEnv
import java.sql.Timestamp

class ProjectDetail (
    val id : Long,
    val supportedLanguage : SupportedLanguage,
    val supportedServer: SupportedServer,
    val supportedDatabase: SupportedDatabase?,
    val projectPlan: ProjectPlan,
    val branch: String,
    val buildPath: String,
    val buildTool: BuildTool,
    val isRunTest: Boolean,
    val dbUserName: String?,
    val dbUserPassword: String?,
    val env: List<ProjectEnv>?,
    val createdAt: Timestamp?,
    val updatedAt: Timestamp?
)
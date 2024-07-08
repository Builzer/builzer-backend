package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildTool
import com.builzer.backend.project.adapter.out.persistence.entity.json.ProjectEnv

class ProjectDetail (
    val id : Long,
    val supportedLanguage : SupportedLanguage,
    val supportedServer: SupportedServer,
    val supportedDatabase: SupportedDatabase?,
    val branch: String,
    val buildPath: String,
    val buildTool: BuildTool,
    val isRunTest: Boolean,
    val dbUserName: String?,
    val dbUserPassword: String?,
    val env: List<ProjectEnv>?
)
package com.builzer.backend.project.adapter.`in`.web.response

import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildTool

data class ProjectSettingResponse(
    val projectId: Long,
    val projectDetailId: Long,
    val languageSpec: String,
    val buildTool: BuildTool,
    val serverSpec: String,
    val dbSpec: String?
)

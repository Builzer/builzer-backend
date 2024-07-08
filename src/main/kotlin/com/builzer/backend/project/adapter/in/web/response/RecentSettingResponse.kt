package com.builzer.backend.project.adapter.`in`.web.response

data class RecentSettingResponse (
    val plan: ProjectPlanResponse,
    val settings: List<ProjectSettingResponse>
)
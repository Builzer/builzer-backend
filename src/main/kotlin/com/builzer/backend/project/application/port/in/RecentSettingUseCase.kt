package com.builzer.backend.project.application.port.`in`

import com.builzer.backend.project.adapter.`in`.web.response.RecentSettingResponse

interface RecentSettingUseCase {
    fun getRecentSettingList(memberId: Long): List<RecentSettingResponse>
}
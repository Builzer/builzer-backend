package com.builzer.backend.project.adapter.`in`.web.response

import java.math.BigDecimal

data class ProjectPlanResponse(
    val planName: String,
    val planPrice: BigDecimal,
    val planExplanation: String
)

package com.builzer.backend.project.domain

import java.math.BigDecimal

class ProjectPlan(
    val id: Long,
    val planType: String,
    val planPrice: BigDecimal,
    val planExplanation: String
)

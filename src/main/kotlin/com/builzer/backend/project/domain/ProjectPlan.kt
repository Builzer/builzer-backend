package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.ProjectPlanJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectPlanType
import java.math.BigDecimal

class ProjectPlan private constructor(
        val id: Long,
        val planType: ProjectPlanType,
        val planPrice: BigDecimal,
        val planExplanation: String?) {

    companion object {

        fun of(projectPlanJpaEntity: ProjectPlanJpaEntity): ProjectPlan {
            return ProjectPlan(
                    id = projectPlanJpaEntity.id!!,
                    planType = projectPlanJpaEntity.planType,
                    planPrice = projectPlanJpaEntity.planPrice,
                    planExplanation = projectPlanJpaEntity.planExplanation
            )
        }
    }
}
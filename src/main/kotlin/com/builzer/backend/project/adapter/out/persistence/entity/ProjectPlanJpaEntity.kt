package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectPlan
import jakarta.persistence.*

@Entity
@Table(name = "project_plan")
class ProjectPlanJpaEntity(

        @Enumerated(EnumType.STRING)
        @Column(name = "plan_name", nullable = false)
        val planName: ProjectPlan,

        @Column(name = "plan_price", nullable = false)
        val planPrice: Double,

        @Column(name = "plan_explanation")
        val planExplanation: String? = null

) : BaseTimeEntity() {

    @Id
    @Column(name = "project_plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
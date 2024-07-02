package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectStatus
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "project")
class ProjectJpaEntity(

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_plan_id", nullable = false, updatable = false)
        val projectPlanJpaEntity: ProjectPlanJpaEntity,

        @Column(name = "project_name", nullable = false)
        val projectName: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "project_status", nullable = false)
        val projectStatus: ProjectStatus,

        @Column(name = "domain", nullable = false, updatable = false)
        val domain: String,

        @Column(name = "git_repository_name", nullable = false)
        val gitRepositoryName: String,

        @Column(name = "is_private_repository", nullable = false)
        val isPrivateRepository: Boolean,

        @Column(name = "last_disabled_at")
        val lastDisabledAt: Timestamp? = null,

        @Column(name = "last_paid_at")
        val lastPaidAt: Timestamp? = null

) : BaseTimeEntity() {

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildStatus
import jakarta.persistence.*

@Entity
@Table(name = "build_history")
class BuildHistoryJpaEntity(

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id", nullable = false, updatable = false)
        val projectJpaEntity: ProjectJpaEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_spec_id", nullable = false, updatable = false)
        val projectSpecJpaEntity: ProjectSpecJpaEntity,

        @Column(name = "build_script")
        val buildScript: String,

        @Column(name = "run_id", nullable = false, updatable = false)
        val runId: Long,

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        val status: BuildStatus,

        @Column(name = "build_time", nullable = false, updatable = false)
        val buildTime: Int,

        @Column(name = "executor", nullable = false, updatable = false)
        val executor: String,

        @Column(name = "last_commit", updatable = false)
        val lastCommit: String? = null,

        @Column(name = "commit_info", updatable = false)
        val commitInfo: String? = null

) : BaseTimeEntity() {

    @Id
    @Column(name = "build_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectStatus
import jakarta.persistence.*
import jakarta.persistence.CascadeType.PERSIST
import jakarta.persistence.CascadeType.REMOVE
import jakarta.persistence.FetchType.LAZY
import java.sql.Timestamp

@Entity
@Table(name = "project")
class ProjectJpaEntity(

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "project_plan_id", nullable = false)
        val projectPlanJpaEntity: ProjectPlanJpaEntity,

        @OneToMany(
                mappedBy = "projectJpaEntity",
                cascade = [PERSIST, REMOVE],
                orphanRemoval = true,
                fetch = LAZY)
        val projectDetailJpaEntity: List<ProjectDetailJpaEntity> = mutableListOf(),

        @Column(name = "project_name", nullable = false)
        val projectName: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "project_status", nullable = false)
        val projectStatus: ProjectStatus = ProjectStatus.CREATING,

        @Column(name = "project_domain_name", nullable = false, updatable = false)
        val projectDomainName: String,

        @Column(name = "git_repository_name", nullable = false)
        val gitRepositoryName: String,

        @Column(name = "is_private_git_repository", nullable = false)
        val isPrivateGitRepository: Boolean,

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
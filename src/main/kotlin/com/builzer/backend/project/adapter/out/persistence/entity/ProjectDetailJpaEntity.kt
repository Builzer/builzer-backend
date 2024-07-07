package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildTool
import com.builzer.backend.project.adapter.out.persistence.entity.json.ProjectEnv
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "project_detail")
class ProjectDetailJpaEntity(

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "supported_language_id", nullable = false, updatable = false)
        val supportedLanguageJpaEntity: SupportedLanguageJpaEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "supported_server_id", nullable = false, updatable = false)
        val supportedServerJpaEntity: SupportedServerJpaEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "supported_database_id", updatable = false)
        var supportedDatabaseJpaEntity: SupportedDatabaseJpaEntity? = null,

        @Column(name = "git_repository_branch_name", nullable = false)
        val gitRepositoryBranchName: String,

        @Column(name = "build_root_path", nullable = false)
        val buildRootPath: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "build_tool", nullable = false)
        val buildTool: BuildTool,

        @Column(name = "database_username")
        var databaseUsername: String? = null,

        @Column(name = "database_password")
        var databasePassword: String? = null,

        @Column(name = "isRunTest", nullable = false)
        var isRunTest: Boolean,

        @Type(JsonType::class)
        @Column(name = "env", columnDefinition = "json")
        var env: List<ProjectEnv> = mutableListOf()

) : BaseTimeEntity() {

    @Id
    @Column(name = "project_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false, updatable = false)
    var projectJpaEntity: ProjectJpaEntity? = null
}
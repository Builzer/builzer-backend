package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.member.adapter.out.persistence.entity.MemberJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.BuildTool
import com.builzer.backend.project.adapter.out.persistence.entity.json.ProjectEnv
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "project_spec")
class ProjectSpecJpaEntity(

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id", nullable = false, updatable = false)
        val memberJpaEntity: MemberJpaEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "supported_language_id", nullable = false, updatable = false)
        val supportedLanguageJpaEntity: SupportedLanguageJpaEntity,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "supported_server_spec_id", nullable = false, updatable = false)
        val supportedServerSpecJpaEntity: SupportedServerSpecJpaEntity,

        @Column(name = "branch", nullable = false, updatable = false)
        val branch: String,

        @Column(name = "build_path", nullable = false, updatable = false)
        val buildPath: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "build_tool", nullable = false, updatable = false)
        val buildTool: BuildTool,

        @Column(name = "isRunTest", nullable = false)
        var isRunTest: Boolean,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "supported_database_id")
        var supportedDatebaseJpaEntity: SupportedDatebaseJpaEntity? = null,

        @Column(name = "db_username")
        var dbUsername: String? = null,

        @Column(name = "db_password")
        var dbPassword: String? = null,

        @Type(JsonType::class)
        @Column(name = "env", columnDefinition = "json")
        var env: List<ProjectEnv> = mutableListOf()

) : BaseTimeEntity() {

    @Id
    @Column(name = "project_spec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
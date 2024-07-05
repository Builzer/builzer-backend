package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.DatabaseType
import jakarta.persistence.*

@Entity
@Table(name = "supported_database")
class SupportedDatabaseJpaEntity(

        @Enumerated(EnumType.STRING)
        @Column(name = "database_type", nullable = false, updatable = false)
        val databaseType: DatabaseType,

        @Column(name = "database_version", nullable = false, updatable = false)
        val databaseVersion: Float,

        @Column(name = "is_used", nullable = false)
        val isUsed: Boolean

) : BaseTimeEntity() {

    @Id
    @Column(name = "supported_database_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.DBType
import jakarta.persistence.*

@Entity
@Table(name = "supported_datebase")
class SupportedDatebaseJpaEntity(

        @Enumerated(EnumType.STRING)
        @Column(name = "db_type", nullable = false, updatable = false)
        val dbType: DBType,

        @Column(name = "db_version", nullable = false, updatable = false)
        val dbVersion: Float,

        @Column(name = "is_used", nullable = false)
        val isUsed: Boolean

) : BaseTimeEntity() {

    @Id
    @Column(name = "supported_database_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
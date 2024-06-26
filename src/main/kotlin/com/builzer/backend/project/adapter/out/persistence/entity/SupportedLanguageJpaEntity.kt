package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageType
import jakarta.persistence.*

@Entity
@Table(name = "supported_language")
class SupportedLanguageJpaEntity(

        @Enumerated(EnumType.STRING)
        @Column(name = "language_type", nullable = false, updatable = false)
        val languageType: LanguageType,

        @Column(name = "language_version", nullable = false, updatable = false)
        val languageVersion: Float,

        @Column(name = "language_provider", nullable = false, updatable = false)
        val languageProvider: LanguageProvider,

        @Column(name = "is_used", nullable = false)
        val isUsed: Boolean

) : BaseTimeEntity() {

    @Id
    @Column(name = "supported_language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
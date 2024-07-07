package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.SupportedLanguageJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageType

class SupportedLanguage private constructor(
        val id: Long,
        val languageType: LanguageType,
        val languageVersion: Float,
        val languageProvider: LanguageProvider,
        val isUsed: Boolean) {

    companion object {

        fun of(supportedLanguageJpaEntity: SupportedLanguageJpaEntity): SupportedLanguage {
            return SupportedLanguage(
                    id = supportedLanguageJpaEntity.id!!,
                    languageType = supportedLanguageJpaEntity.languageType,
                    languageVersion = supportedLanguageJpaEntity.languageVersion,
                    languageProvider = supportedLanguageJpaEntity.languageProvider,
                    isUsed = supportedLanguageJpaEntity.isUsed
            )
        }
    }
}
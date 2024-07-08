package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageType

class SupportedLanguage(
    val id: Long,
    val languageType: LanguageType,
    val languageVersion: Float,
    val languageProvider: LanguageProvider,
    val isUsed: Boolean
)

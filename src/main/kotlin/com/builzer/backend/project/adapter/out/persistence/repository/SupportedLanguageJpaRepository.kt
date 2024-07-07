package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.SupportedLanguageJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SupportedLanguageJpaRepository : JpaRepository<SupportedLanguageJpaEntity, Long> {

    @Query("select sl from SupportedLanguageJpaEntity sl " +
            "where sl.languageType = :languageType " +
            "and sl.languageVersion = :languageVersion " +
            "and sl.languageProvider = :languageProvider " +
            "and sl.isUsed is true")
    fun find(@Param("languageType") languageType: LanguageType,
             @Param("languageVersion") languageVersion: Float,
             @Param("languageProvider") languageProvider: LanguageProvider): SupportedLanguageJpaEntity?
}
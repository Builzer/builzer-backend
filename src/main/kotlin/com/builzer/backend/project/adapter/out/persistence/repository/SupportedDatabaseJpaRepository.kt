package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.SupportedDatabaseJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.DatabaseType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SupportedDatabaseJpaRepository : JpaRepository<SupportedDatabaseJpaEntity, Long> {

    @Query("select sd from SupportedDatabaseJpaEntity sd " +
            "where sd.databaseType = :databaseType " +
            "and sd.databaseVersion = :databaseVersion " +
            "and sd.isUsed is true")
    fun find(@Param("databaseType") databaseType: DatabaseType,
             @Param("databaseVersion") databaseVersion: Float): SupportedDatabaseJpaEntity?
}
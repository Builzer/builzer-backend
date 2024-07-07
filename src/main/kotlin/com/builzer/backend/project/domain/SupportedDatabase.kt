package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.SupportedDatabaseJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.DatabaseType

class SupportedDatabase private constructor(
        val id: Long,
        val databaseType: DatabaseType,
        val dbVersion: Float,
        val isUsed: Boolean) {

    companion object {

        fun of(supportedDatabaseJpaEntity: SupportedDatabaseJpaEntity): SupportedDatabase {
            return SupportedDatabase(
                    id = supportedDatabaseJpaEntity.id!!,
                    databaseType = supportedDatabaseJpaEntity.databaseType,
                    dbVersion = supportedDatabaseJpaEntity.databaseVersion,
                    isUsed = supportedDatabaseJpaEntity.isUsed
            )
        }
    }
}
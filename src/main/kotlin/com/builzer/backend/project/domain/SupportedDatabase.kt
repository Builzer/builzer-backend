package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.DBType

class SupportedDatabase(
    val id: Long,
    val dbType: DBType,
    val dbVersion: Float,
    val isUsed: Boolean
)

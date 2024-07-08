package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.CloudProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerType
import java.math.BigDecimal

class SupportedServer(
    val id: Long,
    val serverName: String,
    val serverVCpu: String,
    val serverMemory: String,
    val serverType: ServerType,
    val cloudProvider: CloudProvider,
    val dollarPrice: BigDecimal,
    val creditPrice: BigDecimal,
    val isUsed: Boolean
)

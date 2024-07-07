package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.SupportedServerJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.CloudProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerName
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerType
import java.math.BigDecimal

class SupportedServer private constructor(
        val id: Long,
        val serverName: ServerName,
        val serverVCpu: Int,
        val serverMemory: String,
        val serverType: ServerType,
        val cloudProvider: CloudProvider,
        val dollarPrice: BigDecimal,
        val creditPrice: BigDecimal,
        val isUsed: Boolean) {

    companion object {

        fun of(supportedServerJpaEntity: SupportedServerJpaEntity): SupportedServer {
            return SupportedServer(
                    id = supportedServerJpaEntity.id!!,
                    serverName = supportedServerJpaEntity.serverName,
                    serverVCpu = supportedServerJpaEntity.serverVCpu,
                    serverMemory = supportedServerJpaEntity.serverMemory,
                    serverType = supportedServerJpaEntity.serverType,
                    cloudProvider = supportedServerJpaEntity.cloudProvider,
                    dollarPrice = supportedServerJpaEntity.dollarPrice,
                    creditPrice = supportedServerJpaEntity.creditPrice,
                    isUsed = supportedServerJpaEntity.isUsed
            )
        }
    }
}
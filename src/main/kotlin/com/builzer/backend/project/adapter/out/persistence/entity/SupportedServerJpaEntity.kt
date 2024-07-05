package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.CloudProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerName
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerType
import jakarta.persistence.*

@Entity
@Table(name = "supported_server")
class SupportedServerJpaEntity(

        @Enumerated(EnumType.STRING)
        @Column(name = "server_name", nullable = false)
        val serverName: ServerName,

        @Column(name = "server_v_cpu", nullable = false)
        val serverVCpu: Int,

        @Column(name = "server_memory", nullable = false)
        val serverMemory: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "server_type", nullable = false)
        val serverType: ServerType,

        @Enumerated(EnumType.STRING)
        @Column(name = "cloud_provider", nullable = false)
        val cloudProvider: CloudProvider,

        @Column(name = "dollar_price", nullable = false)
        val dollarPrice: Double,

        @Column(name = "credit_price", nullable = false)
        val creditPrice: Double,

        @Column(name = "is_used", nullable = false)
        val isUsed: Boolean

) : BaseTimeEntity() {

    @Id
    @Column(name = "supported_server_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
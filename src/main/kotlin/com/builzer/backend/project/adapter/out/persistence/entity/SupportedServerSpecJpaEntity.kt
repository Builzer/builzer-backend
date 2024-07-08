package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.CloudProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerType
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "supported_server_spec")
class SupportedServerSpecJpaEntity(

        @Column(name = "name", nullable = false)
        val serverName: String,

        @Column(name = "v_cpu", nullable = false)
        val serverVCpu: String,

        @Column(name = "memory", nullable = false)
        val serverMemory: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "type", nullable = false)
        val serverType: ServerType,

        @Enumerated(EnumType.STRING)
        @Column(name = "cloud_provider", nullable = false)
        val cloudProvider: CloudProvider,

        @Column(name = "dollar_price", nullable = false)
        val dollarPrice: BigDecimal,

        @Column(name = "credit_price", nullable = false)
        val creditPrice: BigDecimal,

        @Column(name = "is_used", nullable = false)
        val isUsed: Boolean

) : BaseTimeEntity() {

    @Id
    @Column(name = "supported_server_spec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
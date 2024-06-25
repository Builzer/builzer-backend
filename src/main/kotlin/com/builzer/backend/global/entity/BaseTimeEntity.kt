package com.builzer.backend.global.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.sql.Timestamp
import java.time.LocalDateTime

@MappedSuperclass
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity(
        @CreatedDate
        @Column(name = "created_at", nullable = false, updatable = false,
                columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        val createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now()),

        @LastModifiedDate
        @Column(name = "updated_at", nullable = false,
                columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
        val updatedAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())
)

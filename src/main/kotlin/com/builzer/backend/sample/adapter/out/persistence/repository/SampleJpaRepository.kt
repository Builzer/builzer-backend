package com.builzer.backend.sample.adapter.out.persistence.repository

import com.builzer.backend.sample.adapter.out.persistence.entity.SampleJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SampleJpaRepository : JpaRepository<SampleJpaEntity, Long> {
    fun findByName(name: String): SampleJpaEntity?
}
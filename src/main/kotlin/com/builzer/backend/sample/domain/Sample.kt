package com.builzer.backend.sample.domain

import com.builzer.backend.sample.adapter.out.persistence.entity.SampleJpaEntity

class Sample private constructor(val id: Long, val name: String, val isNew: Boolean) {
    companion object {
        fun ofNew(sampleJpaEntity: SampleJpaEntity): Sample {
            return Sample(
                id = sampleJpaEntity.id!!,
                name = sampleJpaEntity.name,
                isNew = true
            )
        }

        fun of(sampleJpaEntity: SampleJpaEntity): Sample {
            return Sample(
                id = sampleJpaEntity.id!!,
                name = sampleJpaEntity.name,
                isNew = false
            )
        }
    }
}
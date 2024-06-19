package com.builzer.backend.sample.adapter.out.persistence

import com.builzer.backend.sample.adapter.out.persistence.entity.SampleJpaEntity
import com.builzer.backend.sample.adapter.out.persistence.repository.SampleJpaRepository
import com.builzer.backend.sample.application.port.out.SamplePort
import com.builzer.backend.sample.domain.Sample
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class SamplePersistenceAdapter(
    val sampleJpaRepository: SampleJpaRepository
) : SamplePort {

    @Transactional
    override fun createOrRead(name: String): Sample {
        val sampleEntity = sampleJpaRepository.findByName(name)

        return if (sampleEntity == null) {
            Sample.ofNew(sampleJpaRepository.save(SampleJpaEntity(name = name)))
        } else {
            Sample.of(sampleEntity)
        }
    }
}
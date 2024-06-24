package com.builzer.backend.member.adapter.out.persistence

import com.builzer.backend.member.adapter.out.persistence.repository.MemberJpaRepository
import com.builzer.backend.member.application.port.out.MemberPort
import com.builzer.backend.member.domain.Member
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberPersistenceAdapter(
    private val memberJpaRepository: MemberJpaRepository
) : MemberPort {
    override fun createOrRead(): Member {
        TODO("create or read")
    }
}
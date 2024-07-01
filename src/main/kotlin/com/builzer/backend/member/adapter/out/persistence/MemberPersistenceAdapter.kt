package com.builzer.backend.member.adapter.out.persistence

import com.builzer.backend.member.adapter.out.persistence.entity.MemberJpaEntity
import com.builzer.backend.member.adapter.out.persistence.repository.MemberJpaRepository
import com.builzer.backend.member.application.port.`in`.command.CreateOrReadCommand
import com.builzer.backend.member.application.port.out.MemberPort
import com.builzer.backend.member.domain.Member
import com.builzer.backend.member.util.MemberMapper
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class MemberPersistenceAdapter(
    private val memberJpaRepository: MemberJpaRepository
) : MemberPort {
    companion object {
        private val mapper = Mappers.getMapper(MemberMapper::class.java)
    }

    @Transactional
    override fun createOrRead(command: CreateOrReadCommand): Member {
        // save() if findByGitEmail() is null
        val memberEntity =
            memberJpaRepository.findByGitEmail(command.email) ?: memberJpaRepository.save(
                MemberJpaEntity(
                    name = command.name,
                    gitEmail = command.email,
                    gitAccessToken = command.githubAccessToken
                )
            )

        return mapper.entityToDomain(memberEntity)
    }
}
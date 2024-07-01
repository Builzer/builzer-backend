package com.builzer.backend.member.util

import com.builzer.backend.member.adapter.`in`.web.request.OAuthRequest
import com.builzer.backend.member.adapter.out.persistence.entity.MemberJpaEntity
import com.builzer.backend.member.application.port.`in`.command.OAuthCommand
import com.builzer.backend.member.domain.Member
import org.mapstruct.Mapper

@Mapper
interface MemberMapper {
    fun requestToCommand(oAuthRequest: OAuthRequest): OAuthCommand
    fun entityToDomain(memberJpaEntity: MemberJpaEntity): Member
}
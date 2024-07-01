package com.builzer.backend.member.application.port.out

import com.builzer.backend.member.application.port.`in`.command.CreateOrReadCommand
import com.builzer.backend.member.domain.Member

interface MemberPort {
    fun createOrRead(command: CreateOrReadCommand): Member
}
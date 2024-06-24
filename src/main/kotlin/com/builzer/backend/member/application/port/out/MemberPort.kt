package com.builzer.backend.member.application.port.out

import com.builzer.backend.member.domain.Member

interface MemberPort {
    fun createOrRead(): Member
}
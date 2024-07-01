package com.builzer.backend.global.config.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken(
    private val memberPayload: MemberPayload,
    authorities: Collection<GrantedAuthority>? = null
) : AbstractAuthenticationToken(authorities) {

    @Deprecated("credential may empty", replaceWith = ReplaceWith("getPrincipal"))
    override fun getCredentials(): Any = Any()

    override fun getPrincipal(): MemberPayload {
        return memberPayload
    }
}
package com.builzer.backend.global.config.security

import com.builzer.backend.global.constant.SecurityConst
import com.builzer.backend.global.util.JwtUtil
import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

// @Component TODO 로그인 통합 테스트 이후 해제
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (!authorizationHeader.startsWith(SecurityConst.BEARER_PREFIX.value)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
            return
        }

        try {
            val parsedClaim = jwtUtil.parseClaims(authorizationHeader)
            setAuthenticationToSecurityContext(parsedClaim)
            filterChain.doFilter(request, response)
        } catch (e: Exception) { // throws exception when token is expired
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.localizedMessage)
        }
    }

    private fun setAuthenticationToSecurityContext(claim: Claims) {
        val authentication = jwtUtil.getAuthentication(claim)
        SecurityContextHolder.getContext().authentication = authentication
    }
}
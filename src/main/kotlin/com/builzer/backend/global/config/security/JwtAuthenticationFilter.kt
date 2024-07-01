package com.builzer.backend.global.config.security

import com.builzer.backend.global.util.JwtUtil
import io.jsonwebtoken.Claims
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (!authorizationHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
            return
        }

        try {
            val parsedClaim = jwtUtil.parseClaims(authorizationHeader)
            setAuthenticationToSecurityContext(parsedClaim)
            filterChain.doFilter(request, response)
        } catch (e: Exception) { //
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.localizedMessage)
        }
    }

    private fun setAuthenticationToSecurityContext(claim: Claims) {
        val authentication = jwtUtil.getAuthentication(claim)
        SecurityContextHolder.getContext().authentication = authentication
    }
}
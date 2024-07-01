package com.builzer.backend.global.util

import com.builzer.backend.global.config.security.JwtAuthenticationToken
import com.builzer.backend.global.config.security.MemberPayload
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.util.*

@Component
class JwtUtil {
    @Value("\${jwt.expiration.access}")
    private lateinit var accessExpiration: String

    @Value("\${jwt.expiration.refresh}")
    private lateinit var refreshExpiration: String

    private val key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    fun generateAccessToken(email: String, memberId: Long, gitAccessToken: String): String {
        return Jwts.builder()
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(expiration(accessExpiration))
            .addClaims(claims(email = email, memberId = memberId, gitAccessToken = gitAccessToken))
            .signWith(key)
            .compact()
    }

    fun generateRefreshToken(email: String, memberId: Long, gitAccessToken: String): String {
        return Jwts.builder()
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(expiration(refreshExpiration))
            .addClaims(claims(email = email, memberId = memberId, gitAccessToken = gitAccessToken))
            .signWith(key)
            .compact()
    }

    @Throws(
        ExpiredJwtException::class,
        UnsupportedJwtException::class,
        MalformedJwtException::class,
        SignatureException::class,
        IllegalArgumentException::class
    )
    fun parseClaims(bearerToken: String): Claims {
        val token = bearerToken.replace("Bearer ", "")
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun getAuthentication(claims: Claims): Authentication {
        return JwtAuthenticationToken(
            memberPayload = MemberPayload(
                id = extractMemberId(claims),
                email = extractEmail(claims),
                gitAccessToken = extractGitAccessToken(claims)
            )
        )
    }

    private fun extractEmail(claims: Claims): String {
        return claims["email"] as String
    }

    private fun extractMemberId(claims: Claims): Long {
        return claims["id"] as Long
    }

    private fun extractGitAccessToken(claims: Claims): String {
        return claims["gitAccessToken"] as String
    }

    private fun claims(email: String, memberId: Long, gitAccessToken: String): Map<String, Any> {
        val claims = mutableMapOf<String, Any>()
        claims["id"] = memberId
        claims["email"] = email
        claims["gitAccessToken"] = gitAccessToken
        return claims
    }

    private fun expiration(expiration: String): Date {
        return Date.from(Instant.now().plusMillis(expiration.toLong()))
    }
}
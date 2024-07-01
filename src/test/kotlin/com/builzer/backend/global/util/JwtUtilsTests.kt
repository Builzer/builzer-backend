package com.builzer.backend.global.util

import io.jsonwebtoken.ExpiredJwtException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
class JwtUtilsTests(private val jwtUtil: JwtUtil) : BehaviorSpec({
    Given("email, memberId, gitAccessToken으로") {
        val email = "test@test.com"
        val memberId = 0L
        val gitAccessToken = "test_token"

        When("액세스 토큰을 생성하면") {
            val accessToken = jwtUtil.generateAccessToken(
                email = email,
                memberId = memberId,
                gitAccessToken = gitAccessToken
            )
            val bearerToken = "Bearer $accessToken"

            Then("유효한 토큰이 생성된다") {
                val validClaims = jwtUtil.parseClaims(bearerToken = bearerToken)

                validClaims["email"] shouldBe email
                validClaims["id"] shouldBe memberId
                validClaims["gitAccessToken"] shouldBe gitAccessToken
            }

            When("일정 시간이 지나면") {
                Thread.sleep(2000)

                Then("일정 시간이 지나면 토큰은 만료된다") {

                    shouldThrow<ExpiredJwtException> {
                        jwtUtil.parseClaims(bearerToken = bearerToken)
                    }
                }
            }
        }
    }
})
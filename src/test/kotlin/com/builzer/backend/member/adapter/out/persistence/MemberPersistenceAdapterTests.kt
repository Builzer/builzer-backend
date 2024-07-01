package com.builzer.backend.member.adapter.out.persistence

import com.builzer.backend.member.application.port.`in`.command.CreateOrReadCommand
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@SpringBootTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberPersistenceAdapterTests(
    private val memberPersistenceAdapter: MemberPersistenceAdapter
) : BehaviorSpec({
    Given("command 객체로") {
        val githubAccessToken = "testToken"
        val name = "testName"
        val email = "testEmail"

        val command = CreateOrReadCommand(
            githubAccessToken = githubAccessToken,
            name = name,
            email = email
        )
        When("member 객체를 생성하면") {
            val member = memberPersistenceAdapter.createOrRead(command)

            Then("id는 null이 아니고, command 객체와 동일한 프로퍼티는 같은 값을 가진다") {
                member.id shouldNotBe null
                member.name shouldBe name
                member.gitEmail shouldBe email
                member.gitAccessToken shouldBe githubAccessToken
            }
        }
    }
})
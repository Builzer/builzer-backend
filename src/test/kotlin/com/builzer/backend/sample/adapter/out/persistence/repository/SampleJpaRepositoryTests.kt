package com.builzer.backend.sample.adapter.out.persistence.repository

import com.builzer.backend.sample.adapter.out.persistence.entity.SampleJpaEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SampleJpaRepositoryTests(
    val sampleJpaRepository: SampleJpaRepository
) : BehaviorSpec({
    Given("name = 'Hello'로") {
        val name = "Hello"

        When("엔티티를 생성하면") {
            var sampleEntity = SampleJpaEntity(name)

            Then("id는 null이다") {
                sampleEntity.id shouldBe null
            }

            When("save 후에는") {
                sampleEntity = sampleJpaRepository.save(sampleEntity)

                Then("id는 null이 아니다") {
                    sampleEntity.id shouldNotBe null
                }
            }
        }
    }
})
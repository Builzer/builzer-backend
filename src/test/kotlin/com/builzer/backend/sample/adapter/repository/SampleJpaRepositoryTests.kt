package com.builzer.backend.sample.adapter.repository

import com.builzer.backend.sample.adapter.out.persistence.entity.SampleJpaEntity
import com.builzer.backend.sample.adapter.out.persistence.repository.SampleJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SampleJpaRepositoryTests : BehaviorSpec() {
    @Autowired
    lateinit var sampleJpaRepository: SampleJpaRepository

    init {
        Given("name = hello인") {
            val name = "hello"

            When("entity를 생성하면") {
                val sample = SampleJpaEntity(name = name)

                Then("id가 존재하지 않는다") {
                    sample.id shouldBe null
                }

                When("save하고 나면") {
                    val savedSample = sampleJpaRepository.save(sample)

                    Then("id가 존재하게 된다") {
                        savedSample.id shouldNotBe null
                    }
                }
            }
        }
    }
}
package com.builzer.backend.sample.adapter.out.persistence

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SamplePersistenceAdapaterTests(
    val samplePersistenceAdapter: SamplePersistenceAdapter
) : BehaviorSpec({
    Given("새로운 name으로") {
        val name = "New"

        When("createOrRead를 실행하면") {
            val sample = samplePersistenceAdapter.createOrRead(name)

            Then("Sample을 새롭게 생성하여 조회한다") {
                sample.isNew shouldBe true
            }
        }
    }
})
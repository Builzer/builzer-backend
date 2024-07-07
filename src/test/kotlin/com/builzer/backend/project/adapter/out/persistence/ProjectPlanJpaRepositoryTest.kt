package com.builzer.backend.project.adapter.out.persistence

import com.builzer.backend.project.adapter.out.persistence.entity.enums.ProjectPlanType
import com.builzer.backend.project.adapter.out.persistence.repository.ProjectPlanJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import java.math.BigDecimal

@ActiveProfiles("test")
@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProjectPlanJpaRepositoryTest(private val projectPlanJpaRepository: ProjectPlanJpaRepository)
    : BehaviorSpec({

    Given("LITE planType 으로") {
        val planType = ProjectPlanType.LITE

        When("findByPlanType를 하면") {
            val projectPlanJpaEntity = projectPlanJpaRepository.findByPlanType(planType = planType)!!

            Then("LITE planType을 가져올 수 있다.") {
                projectPlanJpaEntity.id shouldNotBe null
                projectPlanJpaEntity.planType shouldBe ProjectPlanType.LITE
                projectPlanJpaEntity.planPrice shouldBe BigDecimal.valueOf(330000, 2)
                projectPlanJpaEntity.planExplanation shouldBe "LITE 이용권"
            }
        }
    }
})
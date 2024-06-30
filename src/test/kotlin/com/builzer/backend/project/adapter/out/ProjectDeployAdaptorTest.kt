package com.builzer.backend.project.adapter.out

import com.amazonaws.services.route53.AmazonRoute53
import com.amazonaws.services.route53.model.ListResourceRecordSetsRequest
import com.amazonaws.services.route53.model.ListResourceRecordSetsResult
import com.amazonaws.services.route53.model.ResourceRecordSet
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@ActiveProfiles("test")
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProjectDeployAdaptorTest(
        val testRoute53Client: AmazonRoute53 = mockk(),
        val projectDeployAdaptor: ProjectDeployAdaptor
) : BehaviorSpec({

    beforeTest {
        val resourceRecordSets = listOf(
                ResourceRecordSet().withName("dup.builzer.site."),
                ResourceRecordSet().withName("builzer.site.")
        )
        val listResourceRecordSetsResult = ListResourceRecordSetsResult()
                .withResourceRecordSets(resourceRecordSets)

        every {
            testRoute53Client.listResourceRecordSets(any<ListResourceRecordSetsRequest>())
        } returns listResourceRecordSetsResult
    }

    Given("Route53 레코드 셋으로") {
        val route53ResourceRecordSets = projectDeployAdaptor.getRoute53ResourceRecordSets()

        When("getRegisteredDomainNames을 실행하면") {
            val registeredDomainNames = projectDeployAdaptor.getRegisteredDomainNames(route53ResourceRecordSets)

            Then("Route53에 등록된 도메인들을 가져올 수 있다.") {
                registeredDomainNames shouldContainExactly listOf(
                        "dup.builzer.site",
                        "builzer.site"
                )
            }
        }
    }

    Given("사용자가 유효한 domain name을 입력하여") {
        val domainName = "example.builzer.site"

        When("isValidDomain을 실행하면") {
            val isValidDomain = projectDeployAdaptor.isValidDomain(domainName = domainName)

            Then("true가 나온다.") {
                isValidDomain shouldBe true
            }
        }
    }

    Given("사용자가 유효하지 않은 domain name을 입력하여") {
        val domainName = "example.builzr.site"

        When("isValidDomain을 실행하면") {
            val isValidDomain = projectDeployAdaptor.isValidDomain(domainName = domainName)

            Then("false가 나온다.") {
                isValidDomain shouldBe false
            }
        }
    }
})
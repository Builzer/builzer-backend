package com.builzer.backend.project.application.service

import com.amazonaws.services.route53.AmazonRoute53
import com.amazonaws.services.route53.model.ConflictingDomainExistsException
import com.amazonaws.services.route53.model.ListResourceRecordSetsRequest
import com.amazonaws.services.route53.model.ListResourceRecordSetsResult
import com.amazonaws.services.route53.model.ResourceRecordSet
import com.builzer.backend.project.adapter.`in`.web.request.CheckDomainRequest
import com.builzer.backend.project.adapter.out.ProjectDeployAdaptor
import com.builzer.backend.project.application.port.`in`.command.CheckDomainCommand
import com.builzer.backend.project.application.port.out.ProjectDeployPort
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.apache.coyote.BadRequestException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@ActiveProfiles("test")
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProjectDeployServiceTest(
        val testRoute53Client: AmazonRoute53 = mockk(),
        var projectDeployService: ProjectDeployService
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

    Given("사용자가 유효한 domain name을 입력하여") {
        val domainName = "example.builzer.site"
        val checkDomainRequest = CheckDomainRequest(domainName = domainName)
        val checkDomainCommand = CheckDomainCommand.of(checkDomainRequest = checkDomainRequest)

        When("checkDomainIsAvailable을 실행하면") {

            Then("예외가 발생하지 않는다.") {
                shouldNotThrowAny {
                    projectDeployService.checkDomainIsAvailable(checkDomainCommand)
                }
            }
        }
    }

    Given("사용자가 중복된 domain name을 입력하여") {
        val domainName = "dup.builzer.site"
        val checkDomainRequest = CheckDomainRequest(domainName = domainName)
        val checkDomainCommand = CheckDomainCommand.of(checkDomainRequest = checkDomainRequest)

        When("checkDomainIsAvailable을 실행하면") {

            Then("ConflictingDomainExistsException 예외가 발생한다.") {
                shouldThrowExactly<ConflictingDomainExistsException> {
                    projectDeployService.checkDomainIsAvailable(checkDomainCommand)
                }
            }
        }
    }

    Given("사용자가 유효하지 않은 domain name을 입력하여") {
        val domainName = "example.builzr.site"
        val checkDomainRequest = CheckDomainRequest(domainName = domainName)
        val checkDomainCommand = CheckDomainCommand.of(checkDomainRequest = checkDomainRequest)

        When("checkDomainIsAvailable을 실행하면") {

            Then("BadRequestException 예외가 발생한다.") {
                shouldThrowExactly<BadRequestException> {
                    projectDeployService.checkDomainIsAvailable(checkDomainCommand)
                }
            }
        }
    }
})
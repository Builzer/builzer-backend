package com.builzer.backend.project.adapter.`in`.web

import com.amazonaws.services.route53.AmazonRoute53
import com.amazonaws.services.route53.model.ListResourceRecordSetsRequest
import com.amazonaws.services.route53.model.ListResourceRecordSetsResult
import com.amazonaws.services.route53.model.ResourceRecordSet
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProjectWebAdapterTest(
        private val mockMvc: MockMvc,
        private val testRoute53Client: AmazonRoute53 = mockk()
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

        When("/projects/check-domain을 호출하면") {
            val result = mockMvc.perform(
                    get("/projects/check-domain")
                            .param("domainName", domainName))

            Then("200 OK 를 응답으로 받는다.") {
                result.andExpect(status().isOk)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                        .andExpect(jsonPath("$.message").value(HttpStatus.OK.reasonPhrase))
            }
        }
    }

    Given("사용자가 중복된 domain name을 입력하여") {
        val domainName = "dup.builzer.site"

        When("/projects/check-domain을 호출하면") {
            val result = mockMvc.perform(
                    get("/projects/check-domain")
                            .param("domainName", domainName))

            Then("409 Conflict 를 응답으로 받는다.") {
                result.andExpect(status().isConflict)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(jsonPath("$.code").value(HttpStatus.CONFLICT.value()))
                        .andExpect(jsonPath("$.message").value(HttpStatus.CONFLICT.reasonPhrase))
                        .andExpect(jsonPath("$.data").value("Domain '$domainName' already exists"))
            }
        }
    }

    Given("사용자가 유효하지 않은 domain name을 입력하여") {
        val domainName = "example.builzr.site"

        When("/projects/check-domain을 호출하면") {
            val result = mockMvc.perform(
                    get("/projects/check-domain")
                            .param("domainName", domainName))

            Then("400 Bad Request 를 응답으로 받는다.") {
                result.andExpect(status().isBadRequest)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
                        .andExpect(jsonPath("$.message").value(HttpStatus.BAD_REQUEST.reasonPhrase))
                        .andExpect(jsonPath("$.data").value("Domain '$domainName' is not builzer domain name"))
            }
        }
    }

    Given("사용자가 Regex가 유효하지 않은 domain name을 입력하여") {
        val domainName = "!!!.builzer.site"

        When("/projects/check-domain을 호출하면") {
            val result = mockMvc.perform(
                    get("/projects/check-domain")
                            .param("domainName", domainName))

            Then("400 Bad Request 를 응답으로 받는다.") {
                result.andExpect(status().isBadRequest)
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()))
                        .andExpect(jsonPath("$.message").value(HttpStatus.BAD_REQUEST.reasonPhrase))
                        .andExpect(jsonPath("$.data").value("Domain is not valid regex domain name"))
            }
        }
    }
})
package com.builzer.backend.member.adapter.`in`.web

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 8080)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberWebAdapterTests(
    private val mockMvc: MockMvc,
    private val wireMockServer: WireMockServer = WireMockServer(
        WireMockConfiguration.options().dynamicPort()
    )
) : BehaviorSpec({
    beforeEach {
        wireMockServer.start()
    }

    afterEach {
        wireMockServer.stop()
        wireMockServer.shutdownServer()
    }

    Given("github로부터 생성된 code를 통해") {
        val code = "code_from_github"
        val request = "{\"code\" : \"$code\"}"

        val mockedGitAccessToken = "test_token"

        val mockedAccessTokenResponse =
            "{\"access_token\" : \"$mockedGitAccessToken\", \"scope\" : \"test\", \"token_type\" : \"test\"}"

        val mockedEmail = "test@test.com"
        val mockedName = "test"

        val mockedUserInfoResponse = "{\"email\" : \"$mockedEmail\", \"login\" : \"$mockedName\"}"

        val mockedUserEmailResponse =
            "[{\"email\" : \"$mockedEmail\", \"verified\" : \"true\", \"primary\": \"true\"}]"

        When("oauth 요청을 수행할 경우") {
            stubFor(
                post(urlEqualTo("/login/oauth/access_token"))
                    .willReturn(
                        aResponse()
                            .withStatus(HttpStatus.OK.value())
                            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .withBody(mockedAccessTokenResponse)
                    )
            )

            stubFor(
                get(urlEqualTo("/user"))
                    .willReturn(
                        aResponse()
                            .withStatus(HttpStatus.OK.value())
                            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .withBody(mockedUserInfoResponse)
                    )
            )

            stubFor(
                get(urlEqualTo("/user/emails"))
                    .willReturn(
                        aResponse()
                            .withStatus(HttpStatus.OK.value())
                            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .withBody(mockedUserEmailResponse)
                    )
            )

            val response = mockMvc.perform(
                post("/member/oauth/github")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
            )

            Then("member 정보는 response body에, access token과 refresh token은 header에 반환된다") {
                response.andExpect {
                    status().isOk

                    header().exists("Refresh-Token")
                    header().exists("Access-Token")

                    jsonPath("$.data.gitEmail").value(mockedEmail)
                    jsonPath("$.data.name").value(mockedName)
                    jsonPath("$.data.totalCredit").value(0)
                    jsonPath("$.data.isInvited").value(false)
                }
            }
        }
    }
})
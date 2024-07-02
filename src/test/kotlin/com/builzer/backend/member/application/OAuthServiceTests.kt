package com.builzer.backend.member.application

import com.builzer.backend.member.application.port.`in`.command.OAuthCommand
import com.builzer.backend.member.application.service.OAuthService
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit5.WireMockExtension
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@SpringBootTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ExtendWith(WireMockExtension::class)
@AutoConfigureWireMock(port = 0)
class OAuthServiceTests(
    private val oauthService: OAuthService,
    private val wireMockServer: WireMockServer = WireMockServer(
        WireMockConfiguration.options().dynamicPort()
    )
) : BehaviorSpec({
    beforeTest {
        wireMockServer.start()
    }

    Given("github로부터 생성된 code를 통해") {
        val code = "code_from_github"
        val command = OAuthCommand(code)

        val mockedGitAccessToken = "test_token"

        val mockedAccessTokenResponse =
            "{\"access_token\" : \"$mockedGitAccessToken\", \"scope\" : \"test\", \"token_type\" : \"test\"}"

        val mockedEmail = "test@test.com"
        val mockedName = "test"

        val mockedUserInfoResponse = "{\"email\" : \"$mockedEmail\", \"login\" : \"$mockedName\"}"

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

            val oauthResponse = oauthService.signInOrSignUp(command)

            Then("db에 저장되어 있거나, github로부터 가져온 member 정보와 동일한 member 정보, access, refresh token을 반환한다") {
                oauthResponse.member.name shouldBe mockedName
                oauthResponse.member.gitEmail shouldBe mockedEmail
                oauthResponse.member.gitAccessToken shouldBe mockedGitAccessToken
                oauthResponse.accessToken shouldNotBe null
                oauthResponse.refreshToken shouldNotBe null
            }
        }
    }

    afterTest {
        wireMockServer.stop()
    }
})
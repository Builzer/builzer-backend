package com.builzer.backend.project.application.service

import com.builzer.backend.global.util.FileUtils
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import java.net.HttpURLConnection
import java.net.URL

class RepoInfoServiceTest : FunSpec({
    val githubMockServer = WireMockServer(0)
    listener(WireMockListener(githubMockServer, ListenerMode.PER_SPEC))

    test("사용자 레포지토리 목록 얻어오기") {

        val expected = FileUtils.readFileToString("src/test/resources/mock/github/user-repos.json")

        githubMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/user/repos"))
                .willReturn(
                    WireMock.ok()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expected)
                )
        )

        val connection =
            URL("http://localhost:${githubMockServer.port()}/user/repos").openConnection() as HttpURLConnection
        connection.responseCode shouldBe 200

        val response = connection.inputStream.bufferedReader().use { it.readText() }
        response shouldBe expected
    }

})
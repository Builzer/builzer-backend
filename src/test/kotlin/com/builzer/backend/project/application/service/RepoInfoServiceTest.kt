package com.builzer.backend.project.application.service

import com.builzer.backend.project.adapter.out.client.mapper.GithubMapper
import com.builzer.backend.project.adapter.out.client.response.GithubOrgInfoResponse
import com.builzer.backend.project.adapter.out.client.response.GithubRepoInfoResponse
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import org.mapstruct.factory.Mappers
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths

class RepoInfoServiceTest : FunSpec({
    val githubMockServer = WireMockServer(0)
    listener(WireMockListener(githubMockServer, ListenerMode.PER_SPEC))

    val objectMapper =
        jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val mapper = Mappers.getMapper(GithubMapper::class.java)

    test("사용자 레포지토리 목록 얻어오기") {
        val jsonBody =
            Files.readAllBytes(Paths.get("src/test/resources/mock/github/user-repos.json"))
        val expected =
            objectMapper.readValue(String(jsonBody), Array<GithubRepoInfoResponse>::class.java)

        githubMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/user/repos"))
                .willReturn(
                    WireMock.ok()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonBody)
                )
        )

        val connection =
            URI("http://localhost:${githubMockServer.port()}/user/repos")
                .toURL()
                .openConnection() as HttpURLConnection

        val connectionResult = connection.inputStream.bufferedReader().use { it.readText() }
        val response =
            objectMapper.readValue(connectionResult, Array<GithubRepoInfoResponse>::class.java)

        response shouldBe expected
    }

    test("사용자 레포지토리 목록 맵핑") {
        val jsonBody =
            Files.readAllBytes(Paths.get("src/test/resources/mock/github/user-repos.json"))
        val githubRepoInfoResponse =
            objectMapper.readValue(
                String(jsonBody),
                object : TypeReference<List<GithubRepoInfoResponse>>() {}
            )

        val mappingResponse = mapper.toRepoInfo(githubRepoInfoResponse)

        for (index in mappingResponse.indices) {
            githubRepoInfoResponse[index].full_name shouldBe mappingResponse[index].repoName
            githubRepoInfoResponse[index].private shouldBe mappingResponse[index].isPrivate
        }
    }

})
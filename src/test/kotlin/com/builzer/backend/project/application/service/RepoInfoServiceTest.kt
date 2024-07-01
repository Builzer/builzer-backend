package com.builzer.backend.project.application.service

import com.builzer.backend.project.adapter.`in`.web.response.RepoTreeResponse
import com.builzer.backend.project.adapter.out.client.mapper.GithubMapper
import com.builzer.backend.project.adapter.out.client.response.GithubRepoInfoResponse
import com.builzer.backend.project.adapter.out.client.response.GithubTreeInfoResponse
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
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

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

    test("폴더 목록 트리 변환") {
        val jsonBody =
            Files.readAllBytes(Paths.get("src/test/resources/mock/github/trees.json"))
        val githubTreeResponse =
            objectMapper.readValue(
                String(jsonBody),
                object : TypeReference<GithubTreeInfoResponse>() {}
            )

        // depth 내림차순 정렬 맵
        val comparator = compareByDescending<Pair<Int, String>> { it.first }
            .thenBy {it.second}
        val treeMap = TreeMap<Pair<Int, String>, RepoTreeResponse>(comparator)

        for (treeInfo in githubTreeResponse.tree) {
            // 폴더 대상, 파일 제외
            if (treeInfo.type != "tree") {
                continue;
            }

            // depth 0
            if (!treeInfo.path.contains("/")) {
                val tree = RepoTreeResponse(treeInfo.path, treeInfo.sha, treeInfo.path, mutableListOf())
                treeMap[Pair(0, treeInfo.path)] = tree
                continue
            }

            // else : label, depth 구분
            val splitPath = treeInfo.path.split("/")
            val depth = splitPath.size - 1

            val label = splitPath[depth]
            val tree = RepoTreeResponse(label, treeInfo.sha, treeInfo.path, mutableListOf())
            treeMap[Pair(depth, treeInfo.path)] = tree
        }

        val response = mutableListOf<RepoTreeResponse>()

        // depth 내림차순 순회, 자식 -> 부모 순으로 리스트에 넣음
        for ((key, value) in treeMap) {
            if (key.first == 0) {
                response.add(value)
                continue
            }

            val parentPath = value.path.split("/").dropLast(1).joinToString("/")
            val parentKey = Pair(key.first - 1, parentPath)

            if (treeMap.containsKey(parentKey)) {
                treeMap[parentKey]?.children?.add(value)
            }
        }

        response.size shouldBe 3
    }

})
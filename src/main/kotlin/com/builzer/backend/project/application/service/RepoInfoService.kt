package com.builzer.backend.project.application.service

import com.builzer.backend.project.adapter.`in`.web.response.*
import com.builzer.backend.project.adapter.out.client.GithubRepoInfoClient
import com.builzer.backend.project.adapter.out.client.mapper.GithubMapper
import com.builzer.backend.project.application.port.`in`.RepoInfoUseCase
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.util.*

@Service
class RepoInfoService(
    private val githubRepoInfoClient: GithubRepoInfoClient
) : RepoInfoUseCase {

    private val mapper = Mappers.getMapper(GithubMapper::class.java)

    override fun getOrgList(): List<OrgResponse> {
        val githubRepoInfoResponse = githubRepoInfoClient.getOrgInfo()
        return mapper.toOrgInfo(githubRepoInfoResponse)
    }

    override fun getRepoList(
        possession: String,
        orgName: String?
    ): List<RepoResponse> {
        if (orgName.isNullOrBlank()) {      // 개인 레포 목록
            val githubRepoInfoResponse = githubRepoInfoClient.getRepoInfo(possession)
            return mapper.toRepoInfo(githubRepoInfoResponse)
        } else {                            // 조직 레포 목록
            val githubRepoInfoResponse = githubRepoInfoClient.getOrgRepoInfo(orgName)
            return mapper.toRepoInfo(githubRepoInfoResponse)
        }
    }

    override fun getBranchList(owner: String, repoName: String): List<BranchResponse> {
        val githubBranchResponse = githubRepoInfoClient.getBranchInfo(owner, repoName)
        return mapper.toBranchInfo(githubBranchResponse)
    }

    override fun getRepoItemList(
        owner: String,
        repoName: String,
        branch: String,
        path: String
    ): List<RepoItemListResponse> {
        val githubItemInfoResponse =
            githubRepoInfoClient.getItemInfo(owner, repoName, path, branch)
        return mapper.toItemInfo(githubItemInfoResponse)
    }

    override fun getRepoTreeList(owner: String, repoName: String, sha: String): List<RepoTreeResponse> {
        val githubTreeResponse = githubRepoInfoClient.getTreeInfo(owner, repoName, sha, 1)

        // depth 내림차순 정렬 맵
        val comparator = compareByDescending<Pair<Int, String>> { it.first }
            .thenBy { it.second }
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

        return response
    }

}
package com.builzer.backend.project.application.service

import com.builzer.backend.project.adapter.`in`.web.response.ProjectPlanResponse
import com.builzer.backend.project.adapter.`in`.web.response.ProjectSettingResponse
import com.builzer.backend.project.adapter.`in`.web.response.RecentSettingResponse
import com.builzer.backend.project.application.port.`in`.RecentSettingUseCase
import com.builzer.backend.project.application.port.out.ProjectPlanPort
import com.builzer.backend.project.application.port.out.ProjectPort
import com.builzer.backend.project.domain.ProjectDetail
import com.builzer.backend.project.domain.ProjectPlan
import org.springframework.stereotype.Service

@Service
class RecentSettingService(
    private val projectPort: ProjectPort,
    private val projectPlanPort: ProjectPlanPort
) : RecentSettingUseCase {

    override fun getRecentSettingList(memberId: Long): List<RecentSettingResponse> {

        val projectPlans = projectPlanPort.findAll()
        val usersProjectList = projectPort.findOwnerProjectsByMemberId(memberId)

        val settingMap = mutableMapOf<Long, MutableList<ProjectDetail>>()
        val planMap = mutableMapOf<Long, ProjectPlan>()

        projectPlans.forEach { projectPlan ->
            settingMap[projectPlan.id] = mutableListOf()
            planMap[projectPlan.id] = projectPlan
        }

        usersProjectList.forEach { project ->
            project.projectDetailList.forEach { projectDetail ->
                settingMap[projectDetail.projectPlan.id]?.add(projectDetail)
            }
        }

        val response = mutableListOf<RecentSettingResponse>()
        settingMap.forEach { (key, value) ->

            val plan = planMap[key]
            val planResponse = ProjectPlanResponse(
                planName = plan!!.planType,
                planPrice = plan.planPrice,
                planExplanation = plan.planExplanation
            )

            val settingResponse = mutableListOf<ProjectSettingResponse>()
            value.sortBy { it.createdAt }
            value.forEach { setting ->
                settingResponse.add(
                    ProjectSettingResponse(
                        projectId = setting.project.id,
                        projectDetailId = setting.id,
                        languageSpec =
                        setting.supportedLanguage.languageType.toString()
                        + " / "
                        + setting.supportedLanguage.languageVersion,
                        buildTool = setting.buildTool,
                        serverSpec = setting.supportedServer.serverName,
                        dbSpec = setting.supportedDatabase?.dbType.toString()
                        + " / "
                        + setting.supportedDatabase?.dbVersion
                    )
                )
            }

            response.add(RecentSettingResponse(planResponse, settingResponse))
        }

        return response
    }
}
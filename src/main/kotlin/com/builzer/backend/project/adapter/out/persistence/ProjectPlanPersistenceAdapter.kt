package com.builzer.backend.project.adapter.out.persistence

import com.builzer.backend.project.adapter.out.persistence.repository.ProjectPlanJpaRepository
import com.builzer.backend.project.application.port.out.ProjectPlanPort
import com.builzer.backend.project.domain.ProjectPlan
import com.builzer.backend.project.utils.ProjectMapper
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Repository

@Repository
class ProjectPlanPersistenceAdapter(
    private val projectPlanJpaRepository: ProjectPlanJpaRepository
): ProjectPlanPort {
    companion object {
        private val mapper = Mappers.getMapper(ProjectMapper::class.java)
    }

    override fun findAll(): List<ProjectPlan> {
        val projectPlans = projectPlanJpaRepository.findAll();

        return mapper.entityToDomain(projectPlans)
    }

}
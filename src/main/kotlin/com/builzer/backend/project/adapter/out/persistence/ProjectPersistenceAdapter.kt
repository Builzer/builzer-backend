package com.builzer.backend.project.adapter.out.persistence

import com.builzer.backend.project.adapter.out.persistence.repository.ProjectJpaRepository
import com.builzer.backend.project.application.port.out.ProjectPort
import com.builzer.backend.project.domain.Project
import com.builzer.backend.project.utils.ProjectMapper
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class ProjectPersistenceAdapter(
    private val projectJpaRepository: ProjectJpaRepository
): ProjectPort {
    companion object {
        private val mapper = Mappers.getMapper(ProjectMapper::class.java)
    }

    @Transactional(readOnly = true)
    override fun findOwnerProjectsByMemberId(memberId: Long): List<Project> {
        val projectEntities = projectJpaRepository.findOwnerProjectsByMemberId(memberId)

        return mapper.entityToDomain(projectEntities)
    }
}
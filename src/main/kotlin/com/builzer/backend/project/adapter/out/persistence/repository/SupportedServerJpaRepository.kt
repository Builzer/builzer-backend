package com.builzer.backend.project.adapter.out.persistence.repository

import com.builzer.backend.project.adapter.out.persistence.entity.SupportedServerJpaEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.CloudProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerName
import com.builzer.backend.project.adapter.out.persistence.entity.enums.ServerType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SupportedServerJpaRepository : JpaRepository<SupportedServerJpaEntity, Long> {

    @Query("select ss from SupportedServerJpaEntity ss " +
            "where ss.serverName = :serverName " +
            "and ss.serverType = :serverType " +
            "and ss.cloudProvider = :cloudProvider " +
            "and ss.isUsed is true")
    fun find(@Param("serverName") serverName: ServerName,
             @Param("serverType") serverType: ServerType,
             @Param("cloudProvider") cloudProvider: CloudProvider): SupportedServerJpaEntity?
}
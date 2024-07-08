package com.builzer.backend.project.domain

import com.builzer.backend.project.adapter.out.persistence.entity.enums.InvitationStatus
import java.sql.Timestamp

class ProjectUserInvitation (
    val id: Long,
    val project: Project,
    val invitationMail: String,
    val invitationStatus: InvitationStatus,
    val expiredAt: Timestamp
)
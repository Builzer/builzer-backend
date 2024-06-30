package com.builzer.backend.project.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import com.builzer.backend.project.adapter.out.persistence.entity.enums.InvitationStatus
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "project_user_invitation")
class ProjectUserInvitationJpaEntity(

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id", nullable = false, updatable = false)
        val projectJpaEntity: ProjectJpaEntity,

        @Column(name = "invitation_mail", nullable = false, updatable = false)
        val invitationMail: String,

        @Column(name = "expired_at", nullable = false, updatable = false)
        val expiredAt: Timestamp? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "invitation_status", nullable = false)
        var invitationStatus: InvitationStatus = InvitationStatus.PENDING

) : BaseTimeEntity() {

    @Id
    @Column(name = "project_user_invitation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
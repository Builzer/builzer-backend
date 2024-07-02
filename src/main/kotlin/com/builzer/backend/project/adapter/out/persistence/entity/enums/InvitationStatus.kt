package com.builzer.backend.project.adapter.out.persistence.entity.enums

enum class InvitationStatus(val description: String) {
    PENDING("대기"),
    ACCEPTANCE("수락"),
    REJECTION("거부")
}
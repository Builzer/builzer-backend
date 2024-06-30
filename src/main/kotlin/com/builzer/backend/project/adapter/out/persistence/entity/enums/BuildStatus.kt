package com.builzer.backend.project.adapter.out.persistence.entity.enums

enum class BuildStatus(val description: String) {
    PROGRESS("진행중"),
    SUCCESS("성공"),
    FAIL("실패")
}
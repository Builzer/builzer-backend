package com.builzer.backend.project.adapter.out.persistence.entity.enums

enum class ProjectStatus(val description: String) {
    CREATING("생성중"),
    RUNNING("실행중"),
    STOPPING("중지중"),
    STOP("중지"),
    TERMINATING("종료중"),
    TERMINATED("종료")
}
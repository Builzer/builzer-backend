package com.builzer.backend.project.application.port.`in`

import com.builzer.backend.project.application.port.`in`.command.CheckDomainCommand

interface ProjectDeployUseCase {

    fun checkDomainIsAvailable(checkDomainCommand: CheckDomainCommand)
}
package com.builzer.backend.project.application.port.`in`.command

import com.builzer.backend.project.adapter.`in`.web.request.CheckDomainRequest

class CheckDomainCommand(val domainName: String) {

    companion object {
        fun of(checkDomainRequest: CheckDomainRequest): CheckDomainCommand {
            return CheckDomainCommand(domainName = checkDomainRequest.domainName)
        }
    }
}
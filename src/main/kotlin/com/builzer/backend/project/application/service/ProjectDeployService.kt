package com.builzer.backend.project.application.service

import com.amazonaws.services.route53.model.ConflictingDomainExistsException
import com.builzer.backend.project.application.port.`in`.ProjectDeployUseCase
import com.builzer.backend.project.application.port.`in`.command.CheckDomainCommand
import com.builzer.backend.project.application.port.out.ProjectDeployPort
import com.builzer.backend.project.utils.ProjectDomainUtils.Companion.BUILZER_DOMAIN_NAME
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service

@Service
class ProjectDeployService(private val projectDeployPort: ProjectDeployPort) : ProjectDeployUseCase {

    override fun checkDomainIsAvailable(checkDomainCommand: CheckDomainCommand) {
        val domainName = checkDomainCommand.domainName

        val route53ResourceRecordSets = projectDeployPort.getRoute53ResourceRecordSets()
        val registeredDomainNames = projectDeployPort.getRegisteredDomainNames(
                route53ResourceRecordSets = route53ResourceRecordSets)
        if (!registeredDomainNames
                        .any { registeredDomainName -> registeredDomainName == BUILZER_DOMAIN_NAME }) {
            throw RuntimeException("Could not find builzer domain in Route53")
        }

        val hasRegisteredDomain = registeredDomainNames
                .any { registeredDomainName -> registeredDomainName == domainName }
        if (hasRegisteredDomain) {
            throw ConflictingDomainExistsException("Domain '$domainName' already exists")
        }

        val isValidDomain = projectDeployPort.isValidDomain(domainName = domainName)
        if (!isValidDomain) {
            throw BadRequestException("Domain '$domainName' is not builzer domain name")
        }
    }
}
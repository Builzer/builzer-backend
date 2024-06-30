package com.builzer.backend.project.application.port.out

import com.amazonaws.services.route53.model.ResourceRecordSet

interface ProjectDeployPort {

    fun getRoute53ResourceRecordSets(): List<ResourceRecordSet>

    fun getRegisteredDomainNames(route53ResourceRecordSets: List<ResourceRecordSet>): List<String>

    fun isValidDomain(domainName: String): Boolean
}
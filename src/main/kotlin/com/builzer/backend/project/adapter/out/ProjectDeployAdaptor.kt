package com.builzer.backend.project.adapter.out

import com.amazonaws.services.route53.AmazonRoute53
import com.amazonaws.services.route53.model.ListResourceRecordSetsRequest
import com.amazonaws.services.route53.model.ResourceRecordSet
import com.builzer.backend.project.application.port.out.ProjectDeployPort
import com.builzer.backend.project.utils.ProjectDomainUtils.Companion.BUILZER_DOMAIN_NAME
import com.builzer.backend.project.utils.ProjectDomainUtils.Companion.DOMAIN_DELIMITER
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ProjectDeployAdaptor(private val route53Client: AmazonRoute53) : ProjectDeployPort {

    @Value("\${aws.route53.hostedZoneId}")
    lateinit var hostedZoneId: String

    override fun getRoute53ResourceRecordSets(): List<ResourceRecordSet> {
        val recordSetsRequest = ListResourceRecordSetsRequest()
                .withHostedZoneId(hostedZoneId)
        val listResourceRecordSets = route53Client
                .listResourceRecordSets(recordSetsRequest)
                .withMaxItems("1")
        return listResourceRecordSets.resourceRecordSets
    }

    override fun getRegisteredDomainNames(route53ResourceRecordSets: List<ResourceRecordSet>)
            : MutableList<String> {
        return route53ResourceRecordSets.map { resourceRecordSet ->
            resourceRecordSet.name.removeSuffix(".")
        }.toMutableList()
    }

    override fun isValidDomain(domainName: String): Boolean {
        val splitBuilzerDomainName = BUILZER_DOMAIN_NAME.split(DOMAIN_DELIMITER)
        val splitDomainName = domainName.split(DOMAIN_DELIMITER)

        // Top level domain 비교
        val builzerTopLevelDomainName = splitBuilzerDomainName[splitBuilzerDomainName.size - 1]
        val topLevelDomainName = splitDomainName[splitDomainName.size - 1]
        if (builzerTopLevelDomainName != topLevelDomainName) {
            return false
        }

        // Second level domain 비교
        val builzerSecondLevelDomainName = splitBuilzerDomainName[splitBuilzerDomainName.size - 2]
        val secondLevelDomainName = splitDomainName[splitDomainName.size - 2]
        if (builzerSecondLevelDomainName != secondLevelDomainName) {
            return false
        }

        return true
    }
}
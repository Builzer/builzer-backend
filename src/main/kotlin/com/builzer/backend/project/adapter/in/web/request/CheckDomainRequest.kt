package com.builzer.backend.project.adapter.`in`.web.request

import jakarta.validation.constraints.Pattern

data class CheckDomainRequest(
        @field:Pattern(
                regexp = "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)(\\.[A-Za-z0-9-]{1,63})*$",
                message = "Domain is not valid regex domain name"
        )
        val domainName: String
)

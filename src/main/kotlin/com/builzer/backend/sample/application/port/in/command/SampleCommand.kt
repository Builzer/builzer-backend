package com.builzer.backend.sample.application.port.`in`.command

import com.builzer.backend.sample.adapter.`in`.web.request.SampleRequest

class SampleCommand(
    val name: String
) {
    companion object {
        fun of(sampleRequest: SampleRequest): SampleCommand {
            return SampleCommand(name = sampleRequest.name)
        }
    }
}
package com.builzer.backend.sample.application.service

import com.builzer.backend.sample.application.port.`in`.SampleUseCase
import com.builzer.backend.sample.application.port.`in`.command.SampleCommand
import com.builzer.backend.sample.application.port.out.SamplePort
import org.springframework.stereotype.Service

@Service
class SampleService(
    private val samplePort: SamplePort
) : SampleUseCase {
    override fun put(sampleCommand: SampleCommand): String {
        val sample = samplePort.createOrRead(name = sampleCommand.name)

        return if (sample.isNew) {
            "Welcome ${sample.name}, your id is ${sample.id}"
        } else {
            "Hello ${sample.name}, your id is ${sample.id}"
        }
    }
}
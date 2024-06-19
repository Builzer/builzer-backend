package com.builzer.backend.sample.application.port.`in`

import com.builzer.backend.sample.application.port.`in`.command.SampleCommand


interface SampleUseCase {
    fun put(sampleCommand: SampleCommand): String
}
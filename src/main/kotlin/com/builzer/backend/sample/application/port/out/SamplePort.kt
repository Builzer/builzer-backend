package com.builzer.backend.sample.application.port.out

import com.builzer.backend.sample.domain.Sample

interface SamplePort {
    fun createOrRead(name: String): Sample
}
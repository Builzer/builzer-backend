package com.builzer.backend.sample.adapter.`in`.web

import com.builzer.backend.global.response.ApiResponse
import com.builzer.backend.sample.adapter.`in`.web.request.SampleRequest
import com.builzer.backend.sample.adapter.`in`.web.response.SampleResponse
import com.builzer.backend.sample.application.port.`in`.SampleUseCase
import com.builzer.backend.sample.application.port.`in`.command.SampleCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample")
class SampleWebAdapter(private val sampleUseCase: SampleUseCase) {
    @PostMapping
    fun init(
        @RequestBody sampleRequest: SampleRequest,
    ): ApiResponse<SampleResponse> {
        val sampleCommand = SampleCommand.of(sampleRequest)
        val sampleResponse = SampleResponse(message = sampleUseCase.put(sampleCommand))
        return ApiResponse.ok(sampleResponse)
    }
}
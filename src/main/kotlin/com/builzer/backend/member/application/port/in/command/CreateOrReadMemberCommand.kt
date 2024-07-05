package com.builzer.backend.member.application.port.`in`.command

class CreateOrReadMemberCommand(
    val githubAccessToken: String,
    val name: String,
    val email: String
)
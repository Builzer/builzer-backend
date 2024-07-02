package com.builzer.backend.global.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { request ->
                request.anyRequest().permitAll()
            }
            .csrf { csrf ->
                csrf.disable()
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        /* TODO 로그인 통합 테스트 이후 해제
        http.addFilterAt(
            JwtAuthenticationFilter(JwtUtil()),
            UsernamePasswordAuthenticationFilter::class.java
        )*/

        http.headers { header ->
            header.frameOptions { frameOptions ->
                frameOptions.disable()
            }
        }

        return http.build()
    }
}
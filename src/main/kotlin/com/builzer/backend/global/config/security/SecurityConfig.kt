package com.builzer.backend.global.config.security

import com.builzer.backend.global.util.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { csrf ->
                csrf.disable()
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        http.addFilterAt(
            JwtAuthenticationFilter(JwtUtil()),
            UsernamePasswordAuthenticationFilter::class.java
        )

        http.headers { header ->
            header.frameOptions { frameOptions ->
                frameOptions.disable()
            }
        }

        return http.build()
    }
}
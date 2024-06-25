package com.builzer.backend.global.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/favicon.ico").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { csrf ->
                csrf.ignoringRequestMatchers("/h2-console/**")
            }

        http.headers { header ->
            header.frameOptions { frameOptions ->
                frameOptions.disable()
            }
        }

        return http.build()
    }
}
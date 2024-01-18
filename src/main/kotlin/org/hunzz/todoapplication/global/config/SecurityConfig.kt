package org.hunzz.todoapplication.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .httpBasic { it.disable() } // BasicAuthenticationFilter 제외
            .formLogin { it.disable() } // UsernamePasswordAuthenticationFilter, DefaultLoginPageGenerationFilter, DefaultLogoutPageGenerationFilter 제외
            .csrf { it.disable() } // CSRFFilter 제외
            .build()
    }
}
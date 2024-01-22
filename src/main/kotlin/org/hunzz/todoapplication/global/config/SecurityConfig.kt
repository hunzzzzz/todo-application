package org.hunzz.todoapplication.global.config

import org.hunzz.todoapplication.global.security.filter.JwtAuthenticationFilter
import org.hunzz.todoapplication.global.security.jwt.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint
) {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .httpBasic { it.disable() } // BasicAuthenticationFilter 제외
            .formLogin { it.disable() } // UsernamePasswordAuthenticationFilter, DefaultLoginPageGenerationFilter, DefaultLogoutPageGenerationFilter 제외
            .csrf { it.disable() } // CSRFFilter 제외
            .headers { it.frameOptions { foc -> foc.disable() } } // iframe 차단 해제 (H2-Console 사용을 위한)
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/api/v1/members/login", "api/v1/members/signup", "/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**"
                ).permitAll() // 인증 과정이 필요 없는 특정 URI 설정
                    .anyRequest().authenticated() // 나머지 URI에 대해서는 인증 요구
            }
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java) // 필터 위치 지정
//            .exceptionHandling {
//                it.authenticationEntryPoint(authenticationEntryPoint)
//            } // 에러 처리 (status code를 403이 아닌 401로 처리하기 위한)
            .build()
    }
}
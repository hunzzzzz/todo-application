package org.hunzz.todoapplication.global.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.hunzz.todoapplication.global.exception.InvalidJwtException
import org.hunzz.todoapplication.global.security.jwt.JwtAuthenticationToken
import org.hunzz.todoapplication.global.security.jwt.JwtProvider
import org.hunzz.todoapplication.global.security.jwt.MemberPrincipal
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        getBearerToken(request)
            ?.let {
                jwtProvider.validateToken(it)
                    .onSuccess { jws ->
                        val memberId = jws.payload.subject.toLong()
                        val email = jws.payload.get("email", String::class.java)
                        val role = jws.payload.get("role", String::class.java)
                        val authentication = JwtAuthenticationToken(
                            principal = MemberPrincipal(
                                id = memberId,
                                email = email,
                                roles = setOf(role)
                            ),
                            details = WebAuthenticationDetailsSource().buildDetails(request)
                        )
                        SecurityContextHolder.getContext().authentication = authentication
                    }
                    .onFailure { throw InvalidJwtException() }
            }

        filterChain.doFilter(request, response)
    }

    private fun getBearerToken(request: HttpServletRequest): String? {
        val header = request.getHeader(HttpHeaders.AUTHORIZATION) ?: null // Bearer {JWT}
        return header?.split(" ")?.get(1)
    }
}
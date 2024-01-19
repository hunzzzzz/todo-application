package org.hunzz.todoapplication.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.hunzz.todoapplication.domain.member.dto.response.JwtResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.Date

@Component
class JwtProvider(
    @Value("\${auth.jwt.issuer}") private val issuer: String,
    @Value("\${auth.jwt.secret}") private val secret: String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
    @Value("\${auth.jwt.refreshTokenExpirationHour}") private val refreshTokenExpirationHour: Long
) {

    // 토큰 검증
    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = getKey()
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    // 토큰 생성
    private fun createToken(subject: String, email: String, role: String, exp: Long) =
        Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plus(Duration.ofHours(exp))))
            .claims(getClaim(email, role))
            .signWith(getKey())
            .compact()

    // 토큰 발급
    fun provideToken(subject: String, email: String, role: String) =
        JwtResponse(
            accessToken = createToken(subject, email, role, accessTokenExpirationHour),
            refreshToken = createToken(subject, email, role, refreshTokenExpirationHour)
        )

    private fun getClaim(email: String, role: String) =
        Jwts.claims().add(mapOf("email" to email, "role" to role)).build()

    private fun getKey() = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
}
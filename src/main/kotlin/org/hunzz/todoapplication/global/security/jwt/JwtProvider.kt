package org.hunzz.todoapplication.global.security.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
object JwtProvider {
    private const val SECRET = "5v87n5ytf9c9wn9yfco8w7adh8whonca8f87"
    private val KEY = Keys.hmacShaKeyFor(SECRET.toByteArray())

    // 토큰 검증 → 검증이 되지 않으면 예외 처리
    fun validateToken(jwt: String): Result<*> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }
}
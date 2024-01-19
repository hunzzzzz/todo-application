package org.hunzz.todoapplication.domain.member.dto.response

data class JwtResponse(
    val accessToken: String,
    val refreshToken: String
)
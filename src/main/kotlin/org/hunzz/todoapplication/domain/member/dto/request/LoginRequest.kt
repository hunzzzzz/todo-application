package org.hunzz.todoapplication.domain.member.dto.request

import org.hunzz.todoapplication.domain.member.model.MemberRole

data class LoginRequest(
    val email: String,
    val password: String,
    val role: MemberRole
)
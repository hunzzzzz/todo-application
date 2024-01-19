package org.hunzz.todoapplication.domain.member.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hunzz.todoapplication.domain.member.model.Member
import org.hunzz.todoapplication.global.util.NicknameGenerator.generateNickname
import org.hunzz.todoapplication.global.util.PasswordEncoder

data class SignUpRequest(
//    @NotBlank(message = "Name is required.")
    val name: String,
//    @NotBlank(message = "Email is required.")
    val email: String,
    val nickname: String?,
//    @Pattern(
//        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}${'$'}",
//        message = "Password must contain alphabet, special character, and numbers," +
//                "and also must be 8~12 characters."
//    )
    val password: String
) {
    fun to() =
        Member(name, email, nickname ?: generateNickname(), PasswordEncoder.encode(password))
}
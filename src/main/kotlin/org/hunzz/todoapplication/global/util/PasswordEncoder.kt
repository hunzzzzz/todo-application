package org.hunzz.todoapplication.global.util

import java.util.Base64

object PasswordEncoder {
    fun encode(password: String) = Base64.getEncoder().encodeToString(password.toByteArray())!!

    fun decode(encryptedPassword: String) = Base64.getDecoder().decode(encryptedPassword).decodeToString()
}
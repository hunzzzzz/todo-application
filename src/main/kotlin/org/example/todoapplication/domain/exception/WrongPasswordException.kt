package org.example.todoapplication.domain.exception

data class WrongPasswordException(val password: String) : RuntimeException("$password is wrong password.")
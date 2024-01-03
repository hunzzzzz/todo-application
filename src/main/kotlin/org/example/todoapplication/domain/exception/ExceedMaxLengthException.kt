package org.example.todoapplication.domain.exception

data class ExceedMaxLengthException(
    val name: String, val maxLength: Int
) : RuntimeException("This '$name' has exceeded its maximum length. ($maxLength characters)")
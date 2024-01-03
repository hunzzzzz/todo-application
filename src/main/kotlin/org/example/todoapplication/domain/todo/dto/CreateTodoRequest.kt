package org.example.todoapplication.domain.todo.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class CreateTodoRequest(
    var title: String,
    var contents: String?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    var date: LocalDateTime
)
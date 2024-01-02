package org.example.todoapplication.domain.todo.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class CreateTodoRequest {
    var todoTitle: String = ""
    var todoContents: String = ""

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    var todoDate: LocalDateTime = LocalDateTime.now()
}
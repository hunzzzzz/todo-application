package org.hunzz.todoapplication.domain.todo.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import org.hunzz.todoapplication.domain.todo.model.Todo
import java.time.LocalDateTime

data class AddTodoRequest(
    val title: String,
    val content: String?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val date: LocalDateTime
) {
    fun to() = Todo(title, content, date)
}
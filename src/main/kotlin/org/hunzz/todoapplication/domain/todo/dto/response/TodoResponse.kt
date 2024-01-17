package org.hunzz.todoapplication.domain.todo.dto.response

import org.hunzz.todoapplication.domain.todo.model.Todo
import java.time.LocalDateTime

data class TodoResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(todo: Todo) = TodoResponse(todo.title, todo.content, todo.createdAt, todo.updatedAt)
    }
}
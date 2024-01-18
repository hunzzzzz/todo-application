package org.hunzz.todoapplication.domain.todo.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import org.hunzz.todoapplication.domain.comment.dto.response.CommentResponse
import org.hunzz.todoapplication.domain.todo.model.Todo
import java.time.LocalDateTime

data class TodoResponse(
    val title: String,
    val content: String?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val date: LocalDateTime,
    val isCompleted: Boolean,
    val comments: List<CommentResponse>,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val createdAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(todo: Todo, comments: List<CommentResponse>) =
            TodoResponse(
                todo.title,
                todo.content,
                todo.date,
                todo.isCompleted,
                comments,
                todo.createdAt,
                todo.updatedAt
            )
    }
}
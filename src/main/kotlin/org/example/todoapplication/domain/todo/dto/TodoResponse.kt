package org.example.todoapplication.domain.todo.dto

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Getter
import lombok.Setter
import org.example.todoapplication.domain.comment.entity.Comment
import java.time.LocalDateTime

@Getter
@Setter
data class TodoResponse(
    val id: Long,
    var todoTitle: String,
    var todoContents: String?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    var todoDate: LocalDateTime,
    var isCompleted: Boolean
)
package org.example.todoapplication.domain.todo.dto

import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime

@Getter
@Setter
data class TodoResponse(
    var id: Long,
    var todoTitle: String,
    var todoContents: String,
    var todoDate: LocalDateTime
)
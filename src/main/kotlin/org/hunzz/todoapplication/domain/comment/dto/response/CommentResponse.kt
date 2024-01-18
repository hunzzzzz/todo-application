package org.hunzz.todoapplication.domain.comment.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import org.hunzz.todoapplication.domain.comment.model.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val content: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val createdAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(comment: Comment) = CommentResponse(comment.content, comment.createdAt, comment.updatedAt)
    }
}
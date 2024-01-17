package org.hunzz.todoapplication.domain.comment.dto.response

import org.hunzz.todoapplication.domain.comment.model.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    fun from(comment: Comment) = CommentResponse(comment.content, comment.createdAt, comment.updatedAt)
}
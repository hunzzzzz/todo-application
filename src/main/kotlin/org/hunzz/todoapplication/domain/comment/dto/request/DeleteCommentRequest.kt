package org.hunzz.todoapplication.domain.comment.dto.request

data class DeleteCommentRequest(
    val memberId: Long,
    val todoId: Long,
    val password: String
)
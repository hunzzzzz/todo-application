package org.hunzz.todoapplication.domain.comment.dto.request

import org.hunzz.todoapplication.domain.comment.model.Comment
import org.hunzz.todoapplication.domain.todo.model.Todo

data class AddCommentRequest(
    val content: String,
    val password: String
) {
    fun to(todo: Todo) = Comment(content, password, todo)
}
package org.hunzz.todoapplication.domain.comment.dto.request

import org.hunzz.todoapplication.domain.comment.model.Comment
import org.hunzz.todoapplication.domain.todo.model.Todo

data class AddCommentRequest(
    val content: String
) {
    fun to(todo: Todo) = Comment(content, todo)
}
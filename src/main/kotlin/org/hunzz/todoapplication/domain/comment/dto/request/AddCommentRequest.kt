package org.hunzz.todoapplication.domain.comment.dto.request

import org.hunzz.todoapplication.domain.comment.model.Comment
import org.hunzz.todoapplication.domain.member.model.Member
import org.hunzz.todoapplication.domain.todo.model.Todo

data class AddCommentRequest(
    val memberId: Long,
    val todoId: Long,
    val content: String,
    val password: String
) {
    fun to(todo: Todo, member: Member) = Comment(content, password, todo, member)
}
package org.hunzz.todoapplication.domain.comment.dto.request

import org.hunzz.todoapplication.domain.comment.model.Comment
import org.hunzz.todoapplication.domain.member.model.Member
import org.hunzz.todoapplication.domain.todo.model.Todo
import org.hunzz.todoapplication.global.util.PasswordEncoder

data class AddCommentRequest(
    val memberId: Long,
    val todoId: Long,
    val content: String,
    val password: String
) {
    fun to(todo: Todo, member: Member) = Comment(content, PasswordEncoder.encode(password), todo, member)
}
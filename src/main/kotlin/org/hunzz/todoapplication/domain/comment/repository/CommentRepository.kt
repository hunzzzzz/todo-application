package org.hunzz.todoapplication.domain.comment.repository

import org.hunzz.todoapplication.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllCommentsByTodoId(todoId: Long) : List<Comment>
    fun deleteAllCommentsByTodoId(todoId: Long)
}
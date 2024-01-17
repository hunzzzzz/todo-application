package org.hunzz.todoapplication.domain.comment.service

import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.comment.repository.CommentRepository
import org.hunzz.todoapplication.domain.todo.repository.TodoRepository
import org.hunzz.todoapplication.global.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun addComment(todoId: Long, request: AddCommentRequest): Long {
        val todo = getTodo(todoId)
        return commentRepository.save(request.to(todo)).id!!
    }

    @Transactional
    fun deleteComment(todoId: Long, commentId: Long) {
        getTodo(todoId)
        commentRepository.deleteById(commentId)
    }

    private fun getTodo(todoId: Long) = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo")
}
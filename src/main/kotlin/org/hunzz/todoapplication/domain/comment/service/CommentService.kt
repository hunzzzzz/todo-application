package org.hunzz.todoapplication.domain.comment.service

import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.comment.dto.response.CommentResponse
import org.hunzz.todoapplication.domain.comment.repository.CommentRepository
import org.hunzz.todoapplication.domain.todo.repository.TodoRepository
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
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw Exception()
        return commentRepository.save(request.to(todo)).id!!
    }
}
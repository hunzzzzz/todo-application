package org.example.todoapplication.domain.comment.service

import org.example.todoapplication.domain.comment.dto.CommentResponse
import org.example.todoapplication.domain.comment.entity.Comment
import org.example.todoapplication.domain.comment.repository.CommentRepository
import org.example.todoapplication.domain.exception.EntityNotFoundException
import org.example.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(private val todoRepository: TodoRepository, private val commentRepository: CommentRepository) {
    private fun entityToResponse(comment: Comment) =
        CommentResponse(comment.id!!, comment.contents)

    fun getComments(todoId: Long): List<CommentResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw throw EntityNotFoundException(todoId, "Todo")
        return todo.comments.map { entityToResponse(it) }
    }
}
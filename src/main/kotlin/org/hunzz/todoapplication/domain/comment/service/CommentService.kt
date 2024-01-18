package org.hunzz.todoapplication.domain.comment.service

import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.comment.dto.request.DeleteCommentRequest
import org.hunzz.todoapplication.domain.comment.dto.response.CommentResponse
import org.hunzz.todoapplication.domain.comment.repository.CommentRepository
import org.hunzz.todoapplication.domain.todo.repository.TodoRepository
import org.hunzz.todoapplication.global.exception.ModelNotFoundException
import org.hunzz.todoapplication.global.exception.WrongCommentPasswordException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun findAllCommentsByTodoId(todoId: Long): List<CommentResponse> {
        println(todoId)
        return commentRepository.findAllCommentsByTodoId(todoId).map { CommentResponse.from(it) }
    }

    @Transactional
    fun addComment(request: AddCommentRequest): Long {
        val todo = getTodo(request.todoId)
        return commentRepository.save(request.to(todo)).id!!
    }

    @Transactional
    fun updateComment(commentId: Long, request: AddCommentRequest) =
        validate(request.todoId, commentId, request.password)
            .run { getComment(commentId).update(request) }

    @Transactional
    fun deleteComment(commentId: Long, request: DeleteCommentRequest) =
        validate(request.todoId, commentId, request.password)
            .run { commentRepository.deleteById(commentId) }

    private fun getTodo(todoId: Long) =
        todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo")

    private fun getComment(commentId: Long) =
        commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment")

    private fun validate(todoId: Long, commentId: Long, password: String) {
        val comment = getComment(commentId)
        if (!todoRepository.existsById(todoId)) throw ModelNotFoundException("Todo")
        else if (password != comment.password) throw WrongCommentPasswordException()
    }
}
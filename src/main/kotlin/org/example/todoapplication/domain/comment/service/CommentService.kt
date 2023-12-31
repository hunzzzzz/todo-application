package org.example.todoapplication.domain.comment.service

import jakarta.transaction.Transactional
import org.example.todoapplication.domain.comment.dto.AddCommentRequest
import org.example.todoapplication.domain.comment.dto.CommentResponse
import org.example.todoapplication.domain.comment.dto.DeleteCommentRequest
import org.example.todoapplication.domain.comment.entity.Comment
import org.example.todoapplication.domain.comment.repository.CommentRepository
import org.example.todoapplication.domain.exception.EntityNotFoundException
import org.example.todoapplication.domain.exception.WrongPasswordException
import org.example.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(private val todoRepository: TodoRepository, private val commentRepository: CommentRepository) {
    private fun entityToResponse(comment: Comment) =
        CommentResponse(comment.id!!, comment.contents)

    @Transactional
    fun addComment(todoId: Long, request: AddCommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException(todoId, "Todo")
        val comment = Comment(request.contents, request.password, todo)

        todo.comments.add(comment)
        todoRepository.save(todo)
        return entityToResponse(comment)
    }

    @Transactional
    fun updateComment(todoId: Long, commentId: Long, request: AddCommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException(todoId, "Todo")
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw EntityNotFoundException(commentId, "Comment")

        if (comment.password != request.password)
            throw WrongPasswordException(request.password)
        else {
            todo.comments.remove(comment)
            comment.contents = request.contents
            todo.comments.add(comment)
            todoRepository.save(todo)
            return entityToResponse(comment)
        }
    }

    @Transactional
    fun deleteComment(todoId: Long, commentId: Long, request: DeleteCommentRequest) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw EntityNotFoundException(todoId, "Todo")
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw EntityNotFoundException(commentId, "Comment")

        if (comment.password != request.password)
            throw WrongPasswordException(request.password)
        else {
            todo.comments.remove(comment)
            todoRepository.save(todo)
        }
    }
}
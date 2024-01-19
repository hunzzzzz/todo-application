package org.hunzz.todoapplication.domain.comment.service

import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.comment.dto.request.DeleteCommentRequest
import org.hunzz.todoapplication.domain.comment.dto.response.CommentResponse
import org.hunzz.todoapplication.domain.comment.repository.CommentRepository
import org.hunzz.todoapplication.domain.member.repository.MemberRepository
import org.hunzz.todoapplication.domain.todo.repository.TodoRepository
import org.hunzz.todoapplication.global.exception.ModelNotFoundException
import org.hunzz.todoapplication.global.exception.WrongCommentPasswordException
import org.hunzz.todoapplication.global.util.PasswordEncoder
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val memberRepository: MemberRepository,
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
        val member = getMember(request.memberId)
        val todo = getTodo(request.todoId)
        return commentRepository.save(request.to(todo, member)).id!!
    }

    @Transactional
    fun updateComment(commentId: Long, request: AddCommentRequest) =
        validate(request.memberId, request.todoId, commentId, request.password)
            .run { getComment(commentId).update(request) }

    @Transactional
    fun deleteComment(commentId: Long, request: DeleteCommentRequest) =
        validate(request.memberId, request.todoId, commentId, request.password)
            .run { commentRepository.deleteById(commentId) }

    @Transactional
    fun deleteCommentsByTodoId(todoId: Long) = commentRepository.deleteAllCommentsByTodoId(todoId)

    private fun getMember(memberId: Long) =
        memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member")

    private fun getTodo(todoId: Long) =
        todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo")

    private fun getComment(commentId: Long) =
        commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment")

    private fun validate(memberId: Long, todoId: Long, commentId: Long, password: String) {
        getComment(commentId)
            .let {
                if (memberId != it.member.id) throw ModelNotFoundException("Member")
                else if (todoId != it.todo.id) throw ModelNotFoundException("Todo")
                else if (password != PasswordEncoder.decode(it.password)) throw WrongCommentPasswordException()
            }
    }
}
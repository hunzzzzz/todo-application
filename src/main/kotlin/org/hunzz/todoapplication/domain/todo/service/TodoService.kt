package org.hunzz.todoapplication.domain.todo.service

import org.hunzz.todoapplication.domain.comment.service.CommentService
import org.hunzz.todoapplication.domain.todo.dto.request.AddTodoRequest
import org.hunzz.todoapplication.domain.todo.dto.response.TodoResponse
import org.hunzz.todoapplication.domain.todo.repository.TodoRepository
import org.hunzz.todoapplication.global.exception.ModelNotFoundException
import org.hunzz.todoapplication.global.exception.WrongCriteriaException
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val commentService: CommentService
) {
    @Transactional
    fun findAllTodos() =
        todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
            .map { TodoResponse.from(it, getAllCommentsByTodoId(it.id!!)) }

    @Transactional
    fun findAllTodosWithCriteria(sort: Sort.Direction, criteria: String) =
        try {
            (todoRepository.findAll(Sort.by(sort, criteria)))
                .map { TodoResponse.from(it, getAllCommentsByTodoId(it.id!!)) }
        } catch (e: Exception) {
            throw WrongCriteriaException(criteria)
        }

    @Transactional
    fun findTodo(todoId: Long) = TodoResponse.from(getTodo(todoId), getAllCommentsByTodoId(todoId))

    @Transactional
    fun addTodo(request: AddTodoRequest) =
        todoRepository.save(request.to()).id!!

    @Transactional
    fun updateTodo(todoId: Long, request: AddTodoRequest) = getTodo(todoId).update(request)

    @Transactional
    fun updateTodoCompletion(todoId: Long) = getTodo(todoId).update()

    @Transactional
    fun deleteTodo(todoId: Long) =
        deleteAllCommentsByTodoId(todoId).run { todoRepository.deleteById(todoId) }

    private fun getTodo(todoId: Long) = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo")

    private fun getAllCommentsByTodoId(todoId: Long) =
        commentService.findAllCommentsByTodoId(todoId)

    private fun deleteAllCommentsByTodoId(todoId: Long) =
        commentService.deleteCommentsByTodoId(todoId)
}
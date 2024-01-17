package org.hunzz.todoapplication.domain.todo.service

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
    private val todoRepository: TodoRepository
) {
    @Transactional
    fun findAllTodos() =
        todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).map { TodoResponse.from(it) }

    @Transactional
    fun findAllTodosWithCriteria(sort: Sort.Direction, criteria: String) =
        try {
            (todoRepository.findAll(Sort.by(sort, criteria))).map { TodoResponse.from(it) }
        } catch (e: Exception) {
            throw WrongCriteriaException(criteria)
        }

    @Transactional
    fun findTodo(todoId: Long) =
        TodoResponse.from(todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo"))

    @Transactional
    fun addTodo(request: AddTodoRequest) =
        todoRepository.save(request.to()).id!!

    @Transactional
    fun updateTodo(todoId: Long, request: AddTodoRequest) =
        (todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo")).update(request)

    @Transactional
    fun updateTodoCompletion(todoId: Long) =
        (todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo")).update()

    @Transactional
    fun deleteTodo(todoId: Long) =
        todoRepository.deleteById(todoId)
}
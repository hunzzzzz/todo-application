package org.hunzz.todoapplication.domain.todo.service

import org.hunzz.todoapplication.domain.todo.dto.request.AddTodoRequest
import org.hunzz.todoapplication.domain.todo.dto.response.TodoResponse
import org.hunzz.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {
    @Transactional
    fun findAllTodos() = todoRepository.findAll().map { TodoResponse.from(it) }

    @Transactional
    fun addTodo(request: AddTodoRequest) = todoRepository.save(request.to()).id!!
}
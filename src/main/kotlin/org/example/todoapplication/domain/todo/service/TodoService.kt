package org.example.todoapplication.domain.todo.service

import org.example.todoapplication.domain.todo.dto.CreateTodoRequest
import org.example.todoapplication.domain.todo.dto.TodoResponse
import org.example.todoapplication.domain.todo.entity.Todo
import org.example.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(val repository: TodoRepository) {
    // Entity → Response
    fun entityToResponse(todo: Todo) = TodoResponse(todo.id!!, todo.title, todo.contents, todo.date)

    fun createTodo(request: CreateTodoRequest) =
        entityToResponse(repository.save(Todo(request.title, request.contents, request.date)))

    fun getAllTodos() = repository.findAll().map { entityToResponse(it) }

    fun getTodoById(id: Long) =
        entityToResponse(repository.findByIdOrNull(id) ?: throw IllegalStateException("존재하지 않는 회원입니다."))

    fun delete(id: Long) = repository.delete(
        repository.findByIdOrNull(id) ?: throw IllegalStateException("존재하지 않는 회원입니다.")
    )
}
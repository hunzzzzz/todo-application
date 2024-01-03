package org.example.todoapplication.domain.todo.service

import jakarta.transaction.Transactional
import org.example.todoapplication.domain.exception.EntityNotFoundException
import org.example.todoapplication.domain.todo.dto.CreateTodoRequest
import org.example.todoapplication.domain.todo.dto.TodoResponse
import org.example.todoapplication.domain.todo.entity.Todo
import org.example.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(val repository: TodoRepository) {
    // Entity → Response
    private fun entityToResponse(todo: Todo)
    = TodoResponse(todo.id!!, todo.title, todo.contents, todo.date)

    @Transactional
    fun createTodo(request: CreateTodoRequest) =
        entityToResponse(repository.save(Todo(request.title, request.contents, request.date)))

    fun getAllTodos() = repository.findAll().map { entityToResponse(it) }

    fun getTodoById(id: Long) =
        entityToResponse(repository.findByIdOrNull(id) ?: throw EntityNotFoundException(id, "Todo"))

    @Transactional
    fun updateTodo(id: Long, request: CreateTodoRequest): TodoResponse {
        val todo = repository.findByIdOrNull(id)

        if (todo == null) return createTodo(request)
        else {
            todo.title = request.title
            todo.contents = request.contents
            todo.date = request.date
            return entityToResponse(repository.save(todo))
        }
    }

    @Transactional
    fun deleteTodo(id: Long) = repository.delete(
        repository.findByIdOrNull(id) ?: throw EntityNotFoundException(id, "Todo")
    )
}
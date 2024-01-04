package org.example.todoapplication.domain.todo.service

import jakarta.transaction.Transactional
import org.example.todoapplication.domain.exception.EntityNotFoundException
import org.example.todoapplication.domain.exception.ExceedMaxLengthException
import org.example.todoapplication.domain.todo.dto.CreateTodoRequest
import org.example.todoapplication.domain.todo.dto.TodoResponse
import org.example.todoapplication.domain.todo.entity.Todo
import org.example.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(val repository: TodoRepository) {
    // Entity → Response
    private fun entityToResponse(todo: Todo) =
        TodoResponse(todo.id!!, todo.title, todo.contents, todo.date, todo.isCompleted)

    private fun isTitleExceedItsLength(title: String) = title.length > 200
    private fun isContentsExceedItsLength(contents: String) = contents.length > 1000

    @Transactional
    fun createTodo(request: CreateTodoRequest): TodoResponse {
        if (isTitleExceedItsLength(request.title))
            throw ExceedMaxLengthException("title", 200)
        else if (request.contents != null && isContentsExceedItsLength(request.contents!!))
            throw ExceedMaxLengthException("contents", 1000)
        else return entityToResponse(repository.save(Todo(request.title, request.contents, request.date)))
    }

    fun getTodos() = repository.findAll(Sort.by(Sort.Direction.ASC, "id")).map { entityToResponse(it) }

    fun getTodoById(id: Long) =
        entityToResponse(repository.findByIdOrNull(id) ?: throw EntityNotFoundException(id, "Todo"))

    fun sortTodosByDate(sort: String): List<TodoResponse> {
        return when (sort) {
            "ASC" -> repository.findAll(Sort.by(Sort.Direction.ASC, "date")).map { entityToResponse(it) }
            "DESC" -> repository.findAll(Sort.by(Sort.Direction.DESC, "date")).map { entityToResponse(it) }
            else -> throw IllegalStateException("잘못된 요청입니다.")
        }
    }

    @Transactional
    fun updateTodo(id: Long, request: CreateTodoRequest): TodoResponse {
        val todo = repository.findByIdOrNull(id)

        if (todo == null) return createTodo(request)
        else if (isTitleExceedItsLength(request.title))
            throw ExceedMaxLengthException("title", 200)
        else if (request.contents != null && isContentsExceedItsLength(request.contents!!))
            throw ExceedMaxLengthException("contents", 1000)
        else {
            todo.title = request.title
            todo.contents = request.contents
            todo.date = request.date
            return entityToResponse(repository.save(todo))
        }
    }

    @Transactional
    fun updateTodoCompletionStatus(id: Long) {
        val todo = repository.findByIdOrNull(id) ?: throw EntityNotFoundException(id, "Todo")
        todo.isCompleted = !todo.isCompleted
        repository.save(todo)
    }

    @Transactional
    fun deleteTodo(id: Long) = repository.delete(
        repository.findByIdOrNull(id) ?: throw EntityNotFoundException(id, "Todo")
    )
}
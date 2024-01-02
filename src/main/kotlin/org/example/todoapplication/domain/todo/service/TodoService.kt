package org.example.todoapplication.domain.todo.service

import org.example.todoapplication.domain.todo.dto.CreateTodoRequest
import org.example.todoapplication.domain.todo.dto.TodoResponse
import org.example.todoapplication.domain.todo.entity.TodoEntity
import org.example.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(val repository: TodoRepository) {
    fun createTodo(request: CreateTodoRequest) {
        val entity = TodoEntity()
        entity.title = request.todoTitle
        entity.contents = request.todoContents
        entity.date = request.todoDate
        repository.save(entity)
    }

    fun getAllTodos(): MutableList<TodoResponse> {
        val list = mutableListOf<TodoResponse>()
        for (entity in repository.findAll()) {
            val todo = TodoResponse()
            todo.todoTitle = entity.title
            todo.todoContents = entity.contents
            todo.todoDate = entity.date
            list.add(todo)
        }
        return list
    }

    fun getTodo(id: String): TodoResponse {
        val entity = repository.findById(id.toLong())
        if (entity.isEmpty)
            throw IllegalStateException("존재하지 않는 회원입니다")
        else {
            val todo = TodoResponse()
            todo.todoTitle = entity.get().title
            todo.todoContents = entity.get().contents
            todo.todoDate = entity.get().date
            return todo
        }
    }

    fun delete(id: String) {
        if (repository.findById(id.toLong()).isEmpty)
            throw IllegalStateException("존재하지 않는 회원입니다")
        else repository.deleteById(id.toLong())
    }
}
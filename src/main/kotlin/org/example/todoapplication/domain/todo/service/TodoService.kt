package org.example.todoapplication.domain.todo.service

import org.example.todoapplication.domain.todo.dto.TodoDTO
import org.example.todoapplication.domain.todo.entity.TodoEntity
import org.example.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(val repository: TodoRepository) {
    fun register(todoDTO: TodoDTO) {
        val entity = TodoEntity()
        entity.title = todoDTO.todoTitle
        entity.contents = todoDTO.todoContents
        entity.date = todoDTO.todoDate
        repository.save(entity)
    }

    fun getAllTodos(): MutableList<TodoDTO> {
        val list = mutableListOf<TodoDTO>()
        for (entity in repository.findAll()) {
            val todo = TodoDTO()
            todo.todoTitle = entity.title
            todo.todoContents = entity.contents
            todo.todoDate = entity.date
            list.add(todo)
        }
        return list
    }

    fun getTodo(id: String): TodoDTO {
        val entity = repository.findById(id.toLong())
        if (entity.isEmpty)
            throw IllegalStateException("존재하지 않는 회원입니다")
        else {
            val todo = TodoDTO()
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
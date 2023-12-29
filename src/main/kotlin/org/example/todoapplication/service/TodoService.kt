package org.example.todoapplication.service

import org.example.todoapplication.dto.TodoDTO
import org.example.todoapplication.entity.TodoEntity
import org.example.todoapplication.repository.TodoRepository
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
}
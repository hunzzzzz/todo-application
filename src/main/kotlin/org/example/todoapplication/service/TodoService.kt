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
}
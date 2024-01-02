package org.example.todoapplication.domain.todo.controller

import org.example.todoapplication.domain.todo.dto.TodoDTO
import org.example.todoapplication.domain.todo.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TodoController(val service: TodoService) {
    @PostMapping("/todo")
    fun register(@RequestBody todoDTO: TodoDTO): TodoDTO {
        service.register(todoDTO)
        return todoDTO
    }

    @GetMapping("/todos")
    fun getAllTodos(): MutableList<TodoDTO> {
        return service.getAllTodos()
    }

    @GetMapping("/todo/{id}")
    fun getTodo(@PathVariable id: String): TodoDTO {
        return service.getTodo(id)
    }

    @DeleteMapping("/todo/{id}")
    fun deleteTodo(@PathVariable id: String) {
        service.delete(id)
    }
}
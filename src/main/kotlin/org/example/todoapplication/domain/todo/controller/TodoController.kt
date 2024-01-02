package org.example.todoapplication.domain.todo.controller

import org.example.todoapplication.domain.todo.dto.TodoDTO
import org.example.todoapplication.domain.todo.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(val service: TodoService) {
    @PostMapping
    fun createTodo(@RequestBody todoDTO: TodoDTO): TodoDTO {
        service.register(todoDTO)
        return todoDTO
    }

    @GetMapping
    fun getTodos(): MutableList<TodoDTO> {
        return service.getAllTodos()
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: String): TodoDTO {
        return service.getTodo(id)
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: String) {
        service.delete(id)
    }
}
package org.example.todoapplication.domain.todo.controller

import org.example.todoapplication.domain.todo.dto.CreateTodoRequest
import org.example.todoapplication.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(val service: TodoService) {
    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest) =
        ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(createTodoRequest))

    @GetMapping
    fun getTodos() = ResponseEntity.status(HttpStatus.OK).body(service.getAllTodos())

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: Long) = ResponseEntity.status(HttpStatus.OK).body(service.getTodoById(id))

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
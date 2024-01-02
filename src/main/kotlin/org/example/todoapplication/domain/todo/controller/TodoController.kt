package org.example.todoapplication.domain.todo.controller

import org.example.todoapplication.domain.todo.dto.CreateTodoRequest
import org.example.todoapplication.domain.todo.dto.TodoResponse
import org.example.todoapplication.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(val service: TodoService) {
    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        service.createTodo(createTodoRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build() // TODO : 추후 수정 필요
    }

    @GetMapping
    fun getTodos(): ResponseEntity<MutableList<TodoResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllTodos())
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: String): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTodo(id))
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: String): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
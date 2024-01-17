package org.hunzz.todoapplication.domain.todo.controller

import org.hunzz.todoapplication.domain.todo.dto.request.AddTodoRequest
import org.hunzz.todoapplication.domain.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/todos")
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping
    fun findAllTodos() = ResponseEntity.ok(todoService.findAllTodos())

    @PostMapping
    fun addTodo(@RequestBody request: AddTodoRequest): ResponseEntity<Unit> =
        ResponseEntity.created(
            URI.create(
                String.format("/api/v1/todos/%d", todoService.addTodo(request))
            )
        ).build()
}
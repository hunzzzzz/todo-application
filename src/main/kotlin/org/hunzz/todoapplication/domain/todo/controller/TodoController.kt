package org.hunzz.todoapplication.domain.todo.controller

import org.hunzz.todoapplication.domain.todo.dto.request.AddTodoRequest
import org.hunzz.todoapplication.domain.todo.service.TodoService
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/todos")
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping("/{todoId}")
    fun findTodo(@PathVariable todoId: Long) =
        ResponseEntity.ok(todoService.findTodo(todoId))

    @GetMapping
    fun findAllTodos() = ResponseEntity.ok(todoService.findAllTodos())

    @GetMapping("/{sort}/")
    fun findAllTodosWithCriteria(@PathVariable sort: Sort.Direction, @RequestParam criteria: String) =
        ResponseEntity.ok(todoService.findAllTodosWithCriteria(sort, criteria))

    @PostMapping
    fun addTodo(@RequestBody request: AddTodoRequest): ResponseEntity<Unit> =
        ResponseEntity.created(
            URI.create("/api/v1/todos/${todoService.addTodo(request)}")
        ).build()

    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable todoId: Long, @RequestBody request: AddTodoRequest): ResponseEntity<Unit> {
        todoService.updateTodo(todoId, request)
        return ResponseEntity.ok().build()
    }

    @PutMapping("convert/{todoId}")
    fun updateTodoCompletion(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.updateTodoCompletion(todoId)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity.noContent().build()
    }
}
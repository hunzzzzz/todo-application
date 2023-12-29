package org.example.todoapplication.controller

import org.example.todoapplication.dto.TodoDTO
import org.example.todoapplication.service.TodoService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TodoController(val service: TodoService) {
    @PostMapping("/todo")
    fun register(@RequestBody todoDTO: TodoDTO): TodoDTO {
        service.register(todoDTO)
        return todoDTO
    }
}
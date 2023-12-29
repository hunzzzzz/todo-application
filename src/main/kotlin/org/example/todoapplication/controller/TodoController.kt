package org.example.todoapplication.controller

import org.example.todoapplication.service.TodoService
import org.springframework.stereotype.Controller

@Controller
class TodoController(val service: TodoService) {
}
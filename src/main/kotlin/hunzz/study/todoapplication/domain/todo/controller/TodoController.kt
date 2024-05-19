package hunzz.study.todoapplication.domain.todo.controller

import hunzz.study.todoapplication.domain.todo.service.TodoService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService
) {
}
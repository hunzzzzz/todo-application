package hunzz.study.todoapplication.domain.todo.service

import hunzz.study.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {
}
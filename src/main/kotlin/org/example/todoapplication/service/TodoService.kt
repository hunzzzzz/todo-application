package org.example.todoapplication.service

import org.example.todoapplication.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(val repository: TodoRepository) {

}
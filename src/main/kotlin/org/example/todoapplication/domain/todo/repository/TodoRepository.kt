package org.example.todoapplication.domain.todo.repository

import org.example.todoapplication.domain.todo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {

}
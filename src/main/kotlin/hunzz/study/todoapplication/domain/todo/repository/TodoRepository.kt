package hunzz.study.todoapplication.domain.todo.repository

import hunzz.study.todoapplication.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long>
package hunzz.study.todoapplication.domain.todo.model

import hunzz.study.todoapplication.global.model.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "todos")
class Todo(
    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "date", nullable = false)
    val date: LocalDateTime
) : BaseEntity() {
    @Id
    @Column(name = "todo_id")
    val id: Long? = null
}
package org.hunzz.todoapplication.domain.todo.model

import jakarta.persistence.*
import org.hunzz.todoapplication.domain.todo.dto.request.AddTodoRequest
import org.hunzz.todoapplication.global.entity.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "todos")
class Todo(
    title: String,
    content: String?,
    date: LocalDateTime,
    isCompleted: Boolean = false
) : BaseEntity() {
    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "title")
    var title = title

    @Column(name = "content")
    var content: String? = content

    @Column(name = "date", columnDefinition = "TIMESTAMP(6)", nullable = false)
    var date = date

    @Column(name = "is_completed")
    var isCompleted = isCompleted

    fun update(request: AddTodoRequest) {
        this.title = request.title
        this.content = request.content
        this.date = request.date
    }

    fun update() {
        this.isCompleted = !this.isCompleted
    }
}
package org.example.todoapplication.domain.todo.entity

import jakarta.persistence.*
import org.example.todoapplication.domain.comment.entity.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "Todo")
class Todo(
    var title: String = "",
    var contents: String? = null,
    var date: LocalDateTime = LocalDateTime.now(),
    @Column(name = "is-completed")
    var isCompleted: Boolean = false,
    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
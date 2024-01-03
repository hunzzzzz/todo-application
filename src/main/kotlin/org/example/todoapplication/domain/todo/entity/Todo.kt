package org.example.todoapplication.domain.todo.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime

@Entity
@Getter
@Setter
@Table(name = "Todo")
class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
    var title: String = ""
    var contents: String? = null
    var date: LocalDateTime = LocalDateTime.now()
}
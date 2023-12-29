package org.example.todoapplication.entity

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
@Table(name = "TODO")
class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
    var title: String = ""
    var contents: String = ""
    var date: LocalDateTime = LocalDateTime.now()
}
package org.hunzz.todoapplication.domain.comment.model

import jakarta.persistence.*
import org.hunzz.todoapplication.global.BaseEntity

@Entity
@Table(name = "comments")
class Comment(
    content: String
) : BaseEntity() {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "content")
    var content = content
}
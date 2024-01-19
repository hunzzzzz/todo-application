package org.hunzz.todoapplication.domain.comment.model

import jakarta.persistence.*
import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.member.model.Member
import org.hunzz.todoapplication.domain.todo.model.Todo
import org.hunzz.todoapplication.global.entity.BaseEntity
import org.hunzz.todoapplication.global.util.PasswordEncoder

@Entity
@Table(name = "comments")
class Comment(
    content: String,
    password: String,
    todo: Todo,
    member: Member
) : BaseEntity() {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "content")
    var content = content

    @Column(name = "password")
    var password = password

    @ManyToOne
    @JoinColumn(name = "todo_id")
    var todo: Todo = todo

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member: Member = member

    fun update(request: AddCommentRequest) {
        this.content = request.content
    }
}
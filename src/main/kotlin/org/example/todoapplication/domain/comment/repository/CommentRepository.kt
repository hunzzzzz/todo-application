package org.example.todoapplication.domain.comment.repository

import org.example.todoapplication.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
}
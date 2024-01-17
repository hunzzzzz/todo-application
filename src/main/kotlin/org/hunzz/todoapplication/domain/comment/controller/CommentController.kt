package org.hunzz.todoapplication.domain.comment.controller

import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/todos/{todoId}/comments")
class CommentController(
    val commentService: CommentService
) {
    @PostMapping
    fun addComment(@PathVariable todoId: Long, request: AddCommentRequest): ResponseEntity<Unit> {
        return ResponseEntity.created(URI.create(String.format("%d", commentService.addComment(todoId, request)))).build()
    }
}
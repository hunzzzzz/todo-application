package org.hunzz.todoapplication.domain.comment.controller

import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
        return ResponseEntity.created(
            URI.create(
                String.format(
                    "/api/v1/todos/{todoId}/comments/%d",
                    commentService.addComment(todoId, request)
                )
            )
        ).build()
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        request: AddCommentRequest
    ): ResponseEntity<Unit> {
        commentService.updateComment(todoId, commentId, request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable todoId: Long, @PathVariable commentId: Long): ResponseEntity<Unit> {
        commentService.deleteComment(todoId, commentId)
        return ResponseEntity.noContent().build()
    }
}
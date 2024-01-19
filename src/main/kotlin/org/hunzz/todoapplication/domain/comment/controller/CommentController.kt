package org.hunzz.todoapplication.domain.comment.controller

import org.hunzz.todoapplication.domain.comment.dto.request.AddCommentRequest
import org.hunzz.todoapplication.domain.comment.dto.request.DeleteCommentRequest
import org.hunzz.todoapplication.domain.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/comments")
class CommentController(
    val commentService: CommentService
) {
    @PostMapping
    fun addComment(request: AddCommentRequest): ResponseEntity<Unit> {
        return ResponseEntity.created(
            URI.create("/api/v1/comments/${commentService.addComment(request)}")
        ).build()
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        request: AddCommentRequest
    ): ResponseEntity<Unit> {
        commentService.updateComment(commentId, request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long,
        @RequestBody request: DeleteCommentRequest
    ): ResponseEntity<Unit> {
        commentService.deleteComment(commentId, request)
        return ResponseEntity.noContent().build()
    }
}
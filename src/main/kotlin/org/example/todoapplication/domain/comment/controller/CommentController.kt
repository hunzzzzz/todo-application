package org.example.todoapplication.domain.comment.controller

import org.example.todoapplication.domain.comment.dto.AddCommentRequest
import org.example.todoapplication.domain.comment.dto.DeleteCommentRequest
import org.example.todoapplication.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos/{todoId}/comments")
class CommentController(private val service: CommentService) {
    @PostMapping
    fun addComment(@PathVariable todoId: Long, @RequestBody addCommentRequest: AddCommentRequest) =
        ResponseEntity.status(HttpStatus.CREATED).body(service.addComment(todoId, addCommentRequest))

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody addCommentRequest: AddCommentRequest
    ) = ResponseEntity.status(HttpStatus.OK).body(service.updateComment(todoId, commentId, addCommentRequest))

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody deleteCommentRequest: DeleteCommentRequest
    ) =
        ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(service.deleteComment(todoId, commentId, deleteCommentRequest))
}
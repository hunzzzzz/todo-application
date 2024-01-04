package org.example.todoapplication.domain.comment.controller

import org.example.todoapplication.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos/{todoID}/comments")
class CommentController(private val service: CommentService) {
    @GetMapping
    fun getComments(@PathVariable todoID: Long) = ResponseEntity.status(HttpStatus.OK).body(service.getComments(todoID))
}
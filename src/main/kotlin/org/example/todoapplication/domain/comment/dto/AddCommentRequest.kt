package org.example.todoapplication.domain.comment.dto

data class AddCommentRequest(
    var contents: String,
    var password: String
)
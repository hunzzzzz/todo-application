package org.hunzz.todoapplication.domain.member.controller

import jakarta.validation.Valid
import org.hunzz.todoapplication.domain.member.dto.request.SignUpRequest
import org.hunzz.todoapplication.domain.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<Unit> =
        ResponseEntity.created(
            URI.create("/api/v1/members/${memberService.signUp(request)}")
        ).build()
}
package org.hunzz.todoapplication.domain.member.service

import org.hunzz.todoapplication.domain.member.dto.request.SignUpRequest
import org.hunzz.todoapplication.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun signUp(request: SignUpRequest) = memberRepository.save(request.to()).id!!
}
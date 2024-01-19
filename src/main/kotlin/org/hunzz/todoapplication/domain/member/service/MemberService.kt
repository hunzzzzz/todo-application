package org.hunzz.todoapplication.domain.member.service

import org.hunzz.todoapplication.domain.member.dto.request.LoginRequest
import org.hunzz.todoapplication.domain.member.dto.request.SignUpRequest
import org.hunzz.todoapplication.domain.member.model.Member
import org.hunzz.todoapplication.domain.member.repository.MemberRepository
import org.hunzz.todoapplication.global.exception.InvalidCredentialException
import org.hunzz.todoapplication.global.exception.ModelNotFoundException
import org.hunzz.todoapplication.global.exception.InvalidPasswordException
import org.hunzz.todoapplication.global.security.jwt.JwtProvider
import org.hunzz.todoapplication.global.util.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val jwtProvider: JwtProvider
) {
    @Transactional
    fun signUp(request: SignUpRequest) =
        memberRepository.save(request.to()).id!!

    @Transactional
    fun login(request: LoginRequest) =
        getMemberByEmail(request.email)
            .let {
                validateLoginRequest(it, request)
                jwtProvider.provideToken(
                    subject = it.id.toString(),
                    email = it.email,
                    role = it.role.name
                )
            }

    private fun getMemberByEmail(email: String) =
        memberRepository.findByEmail(email) ?: throw ModelNotFoundException("Member")

    private fun validateLoginRequest(member: Member, request: LoginRequest) {
        if (member.role != request.role) throw InvalidCredentialException()
        else if (PasswordEncoder.decode(member.password) != request.password) throw InvalidPasswordException()
    }
}
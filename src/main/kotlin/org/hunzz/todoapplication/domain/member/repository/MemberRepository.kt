package org.hunzz.todoapplication.domain.member.repository

import org.hunzz.todoapplication.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}
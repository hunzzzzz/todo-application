package org.hunzz.todoapplication.domain.member.model

import jakarta.persistence.*
import org.hunzz.todoapplication.global.entity.BaseEntity

@Entity
@Table(name = "members")
class Member(
    name: String,
    email: String,
    nickname: String?,
    password: String,
    role: MemberRole = MemberRole.MEMBER
) : BaseEntity() {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "name")
    var name = name

    @Column(name = "email")
    var email = email

    @Column(name = "nickname")
    var nickname = nickname

    @Column(name = "password")
    var password = password
  
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role = role

    fun updateForWithdrawal() {
        this.name = "탈퇴한 회원"
        this.nickname = null
        this.password = ""
//        this.role = MemberRole.WITHDRAWN
    }
}
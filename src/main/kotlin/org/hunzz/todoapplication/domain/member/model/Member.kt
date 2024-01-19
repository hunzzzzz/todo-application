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

    @Column(name = "email", unique = true)
    var email = email

    @Column(name = "nickname", unique = true)
    var nickname = nickname

    @Column(name = "password")
    var password = password

    @Column(name = "role")
    var role = role
}
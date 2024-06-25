package com.builzer.backend.member.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "member")
class MemberJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "profile_img", nullable = false)
    var profileImg: Int,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "git_email", nullable = false)
    var gitEmail: String,

    @Column(name = "git_access_token", nullable = false)
    var gitAccessToken: String,

    @Column(name = "last_login_date", nullable = false)
    var lastLoginDate: LocalDateTime,

    @Column(name = "total_credit", nullable = false)
    var totalCredit: Int,

    @Column(name = "quit_date")
    var quitDate: LocalDateTime?,

    @Column(name = "customer_key", nullable = false)
    var customerKey: String,

    @Column(name = "billing_key")
    var billingKey: String?

) : BaseTimeEntity()
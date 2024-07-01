package com.builzer.backend.member.adapter.out.persistence.entity

import com.builzer.backend.global.entity.BaseTimeEntity
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

@Entity(name = "member")
class MemberJpaEntity(
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "profile_img", nullable = false)
    var profileImg: Int = Random.nextInt(1, 11), // TODO : 숫자 범위 정윤이한테 체크

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "git_email", nullable = false)
    var gitEmail: String,

    @Column(name = "git_access_token", nullable = false)
    var gitAccessToken: String,

    @Column(
        name = "last_login_date",
        nullable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    var lastLoginDate: Timestamp = Timestamp.valueOf(LocalDateTime.now()),

    @Column(name = "total_credit", nullable = false)
    var totalCredit: Int = 0,

    @Column(name = "quit_date")
    var quitDate: Timestamp? = null,

    @Column(name = "customer_key", nullable = false, unique = true)
    var customerKey: UUID = UUID.randomUUID(),

    @Column(name = "billing_key")
    var billingKey: String? = null

) : BaseTimeEntity()
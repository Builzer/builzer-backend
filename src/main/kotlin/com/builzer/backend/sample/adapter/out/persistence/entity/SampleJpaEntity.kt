package com.builzer.backend.sample.adapter.out.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "sample")
class SampleJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(name = "name", nullable = false)
    var name: String = ""
) {
    constructor(name: String) : this(null, name)
}
package com.springcourse.findjob.models

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "requirements", schema = "findjob")
data class VacancyDescription(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", nullable = false)
    val id: Int = 0,
    var company: String = "",
    var schedule: String? = "",
    var phoneNum: String? = "",
)
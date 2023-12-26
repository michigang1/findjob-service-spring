package com.springcourse.findjob.models

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "requirements", schema = "findjob")
data class VacancyRequirements(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", nullable = false)
    val id: Int = 0,
    var age: Int? = 0,
    var experienceAge: Int? = 0,
    var educationDegree: String? = "",
    var otherReqs: String? = "",
)

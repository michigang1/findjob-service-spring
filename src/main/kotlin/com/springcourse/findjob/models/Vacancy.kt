package com.springcourse.findjob.models

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.annotations.ManyToAny
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "vacancy", schema = "findjob")
data class Vacancy(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", nullable = false)
    val id: Int = 0,

    @Column(name = "title", nullable = false)
    val title: String = "",

    @OneToOne
    @JoinColumn(name = "descriptionId", nullable = false)
    val description: VacancyDescription,

    @OneToOne
    @JoinColumn(name = "requirementsId", nullable = false)
    var requirementsId: VacancyRequirements
) {

}

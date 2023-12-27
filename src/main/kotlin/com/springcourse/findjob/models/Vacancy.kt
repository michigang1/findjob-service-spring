package com.springcourse.findjob.models

import jakarta.persistence.*

@Entity
@Table(name = "vacancy", schema = "findjob")
@NamedNativeQuery(
    name = "Vacancy.getById",
    query = "SELECT * FROM vacancy WHERE id = ?",
    resultClass = Vacancy::class
)

@NamedNativeQuery(
    name = "Vacancy.getAll",
    query = "SELECT * FROM vacancy",
    resultClass = Vacancy::class
)
data class Vacancy(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0,
    @Column(name = "title")
    val title: String = "",

    @Column(name = "company")
    val company: String = "",

    @Column(name = "schedule")
    val schedule: String? = "",

    @Column(name = "phoneNum")
    val phoneNum: String? = "",

    @Column(name = "age")
    val age: Int? = 0,

    @Column(name = "experienceAge")
    val experienceAge: Int? = 0,

    @Column(name = "educationDegree")
    val educationDegree: String? = "",

    @Column(name = "otherReqs")
    val otherReqs: String? = ""


)

package com.springcourse.findjob.models

import org.springframework.context.annotation.Bean

data class Vacancy(
    var id: Int = 0,
    val title: String = "None",
    val description: VacancyDescription,
    val requirements: VacancyRequirements,
)

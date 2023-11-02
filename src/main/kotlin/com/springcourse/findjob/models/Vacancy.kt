package com.springcourse.findjob.models

import org.springframework.context.annotation.Bean

data class Vacancy(
    var id: Int = 0,
    var title: String = "None",
    var description: VacancyDescription,
    var requirements: VacancyRequirements,
)

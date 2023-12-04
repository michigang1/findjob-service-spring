package com.springcourse.findjob.models

data class Vacancy(
    var id: Int = 0,
    var title: String? = "",
    var description: VacancyDescription?,
    var requirements: VacancyRequirements?
)

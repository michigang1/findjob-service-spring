package com.springcourse.findjob.models

data class Vacancy(
    var id: Int = 0,
    var title: String? = null,
    var description: VacancyDescription?,
    var requirements: VacancyRequirements?
)

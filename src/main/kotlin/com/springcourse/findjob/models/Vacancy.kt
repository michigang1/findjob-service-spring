package com.springcourse.findjob.models

import com.fasterxml.jackson.databind.BeanDescription
import javax.swing.SizeRequirements

data class Vacancy(
    var id: Int = 0,
    val title: String,
    val description: VacancyDescription,
    val requirements: VacancyRequirements,
)

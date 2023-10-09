package com.springcourse.findjob.models

import com.fasterxml.jackson.databind.BeanDescription
import javax.swing.SizeRequirements

data class Vacancy(
    val title: String,
    val description: String,
    val requirements: String,
)

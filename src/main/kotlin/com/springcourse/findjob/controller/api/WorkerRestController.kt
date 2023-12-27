package com.springcourse.findjob.controller.api

import com.springcourse.findjob.models.VacancyDto
import org.springframework.http.ResponseEntity

interface WorkerRestController {
    fun getAllVacancies(): ResponseEntity<List<VacancyDto>>

    fun getAllVacanciesByKeyword(keyword: String): ResponseEntity<List<VacancyDto>>

    fun getAllVacanciesByAge(age: Int): ResponseEntity<List<VacancyDto>>
}

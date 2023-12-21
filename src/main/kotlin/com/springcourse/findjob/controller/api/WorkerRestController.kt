package com.springcourse.findjob.controller.api

import com.springcourse.findjob.models.Vacancy
import org.springframework.http.ResponseEntity

interface WorkerRestController {
    fun getAllVacancies(): ResponseEntity<List<Vacancy>>

    fun getAllVacanciesByKeyword(keyword: String): ResponseEntity<List<Vacancy>>

    fun getAllVacanciesByAge(age: Int): ResponseEntity<Any>
}

package com.springcourse.findjob.controller.api

import com.springcourse.findjob.models.Vacancy
import org.springframework.http.ResponseEntity

interface CompanyRestController {
    fun getCompanyVacancies(company: String): ResponseEntity<Any>

    fun getVacancyById(id: Int): ResponseEntity<Any>

    fun createVacancy(vacancy: Vacancy, company: String): ResponseEntity<Int>

    fun upgradeVacancy(id: Int, vacancy: Vacancy)

    fun deleteVacancy(id: Int, company: String)
}

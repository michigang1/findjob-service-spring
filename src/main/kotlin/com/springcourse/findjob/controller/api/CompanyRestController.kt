package com.springcourse.findjob.controller.api

import com.springcourse.findjob.models.VacancyDto
import org.springframework.http.ResponseEntity

interface CompanyRestController {
    fun getCompanyVacancies(company: String): ResponseEntity<List<VacancyDto>>

    fun getVacancyById(id: Int): ResponseEntity<VacancyDto>

    fun createVacancy(vacancyDto: VacancyDto, company: String)

    fun upgradeVacancy(id: Int, vacancyDto: VacancyDto)

    fun deleteVacancy(id: Int, company: String)
}

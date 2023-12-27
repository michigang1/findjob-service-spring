package com.springcourse.findjob.service

import com.springcourse.findjob.models.VacancyDto

interface GeneralService{
    fun createVacancy(vacancyDto: VacancyDto)
    fun upgradeVacancy(id: Int, vacancyDto: VacancyDto)
    fun deleteVacancy(id: Int)
    fun getAllVacancies(): List<VacancyDto>
    fun getCompanyVacancies(name: String): List<VacancyDto>
    fun getVacancyById(id: Int): VacancyDto
    fun getByKeyWordVacancy(keyWord: String): List<VacancyDto>
    fun getByFilter(vacancyDtoFilter: VacancyDto): List<VacancyDto>

    fun getVacanciesByAge(age: Int): List<VacancyDto>
}
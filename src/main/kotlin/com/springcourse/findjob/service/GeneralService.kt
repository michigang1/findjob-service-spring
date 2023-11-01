package com.springcourse.findjob.service

import com.springcourse.findjob.models.Vacancy

interface GeneralService{
    fun createVacancy(vacancy: Vacancy)
    fun upgradeVacancy(id: Int, vacancy: Vacancy)
    fun deleteVacancy(id: Int)
    fun getAllVacancies(): List<Vacancy>
    fun getCompanyVacancies(name: String): List<Vacancy>
    fun getVacancyById(id: Int): Vacancy
    fun getByKeyWordVacancy(keyWord: String): List<Vacancy>
}
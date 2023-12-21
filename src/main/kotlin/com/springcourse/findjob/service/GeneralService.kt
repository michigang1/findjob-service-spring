package com.springcourse.findjob.service

import com.springcourse.findjob.models.User
import com.springcourse.findjob.models.Vacancy

interface GeneralService{
    fun createVacancy(vacancy: Vacancy): Int
    fun upgradeVacancy(id: Int, vacancy: Vacancy)
    fun deleteVacancy(id: Int)
    fun getAllVacancies(): List<Vacancy>
    fun getCompanyVacancies(name: String): List<Vacancy>
    fun getVacancyById(id: Int): Vacancy
    fun getByKeyWordVacancy(keyWord: String): List<Vacancy>
    fun getByFilter(vacancyFilter: Vacancy): List<Vacancy>

    fun getUserByName(name: String): User

    fun getVacanciesByAge(age: Int): List<Vacancy>
}
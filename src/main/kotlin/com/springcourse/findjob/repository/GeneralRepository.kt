package com.springcourse.findjob.repository

import com.springcourse.findjob.models.User
import com.springcourse.findjob.models.Vacancy

interface GeneralRepository {

    fun createVacancy(vacancy: Vacancy): Int
    fun upgradeVacancy(id: Int, vacancy: Vacancy): Int
    fun deleteVacancy(id: Int) : Int
    fun getAllVacancies(): List<Vacancy>
    fun getByKeyWordVacancy(keyWord: String): List<Vacancy>
    fun getByFilter(vacancyFilter: Vacancy): List<Vacancy>

    fun getUserByName(name: String): User

    fun getVacanciesByAge(age: Int): List<Vacancy>
}
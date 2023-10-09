package com.springcourse.findjob.repository

import com.springcourse.findjob.models.Vacancy

interface GeneralRepository {

    fun createVacancy(vacancy: Vacancy): Boolean
    fun upgradeVacancy(id: Int, vacancy: Vacancy)
    fun deleteVacancy(id: Int)
    fun getAllVacancies(): List<Vacancy>
    fun getByKeyWordVacancy(keyWord: String): List<Vacancy>
}
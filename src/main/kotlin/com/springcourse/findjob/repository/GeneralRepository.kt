package com.springcourse.findjob.repository

import com.springcourse.findjob.models.Vacancy

interface GeneralRepository {
    fun createVacancy(vacanacy: Vacancy)
    fun upgradeVacancy()
    fun deleteVacancy()
    fun getAllVacancies()
    fun getByKeyWordVacancy()
}
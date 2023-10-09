package com.springcourse.findjob.service

import com.springcourse.findjob.models.Vacancy

interface GeneralService{
    fun createVacancy(vacancy: Vacancy): Boolean
    fun upgradeVacancy(id: Int, vacancy: Vacancy)
    fun deleteVacancy(id: Int)
    fun getAllVacancies(): List<Vacancy>
    fun getByKeyWordVacancy(keyWord: String): List<Vacancy>
}
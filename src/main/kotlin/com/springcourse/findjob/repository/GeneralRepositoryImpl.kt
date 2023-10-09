package com.springcourse.findjob.repository

import com.springcourse.findjob.models.Vacancy
import org.springframework.stereotype.Repository

@Repository
class GeneralRepositoryImpl : GeneralRepository {
    val vacancies = mutableListOf<Vacancy>()
    override fun createVacancy(vacancy: Vacancy) = vacancies.add(vacancy)

    override fun upgradeVacancy(id: Int, vacancy: Vacancy) {
        vacancies[id] = vacancy
    }

    override fun deleteVacancy(id: Int) {
        vacancies.removeAt(id)
    }

    override fun getAllVacancies(): List<Vacancy> {
        return vacancies
    }

    override fun getByKeyWordVacancy(keyWord: String): List<Vacancy> {
        return vacancies.filter { it.requirements.contains(keyWord) }
    }
}

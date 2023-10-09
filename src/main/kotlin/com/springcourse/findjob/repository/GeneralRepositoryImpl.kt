package com.springcourse.findjob.repository

import com.springcourse.findjob.models.Vacancy
import org.springframework.stereotype.Repository

@Repository
class GeneralRepositoryImpl : GeneralRepository {
    val vacancies = mutableListOf<Vacancy>()
    init {
        vacancies.add(Vacancy("Java developer", "Offer office, no work experience needed, provide education", "Java experience"))
        vacancies.add(Vacancy("Unity developer", "Remote work, flexible schedule, payout every week", "Unity developer for 3 years, OOP SOLID understanding"))
        vacancies.add(Vacancy("Waiter in luxury restaurant", "5 days of work for 8 hours, educating newcomers, frequent payouts, big tips", "Be able to stand for 8 hours of work"))
    }
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
        return vacancies.filter {
            it.title.contains(keyWord, true) ||
            it.description.contains(keyWord, true) ||
            it.requirements.contains(keyWord, true)
        }
    }
}

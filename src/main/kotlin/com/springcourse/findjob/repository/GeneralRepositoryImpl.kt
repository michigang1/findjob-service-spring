package com.springcourse.findjob.repository

import com.springcourse.findjob.models.Vacancy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class GeneralRepositoryImpl : GeneralRepository {
    @Autowired
    var vacancies = mutableListOf<Vacancy>()
    override fun createVacancy(vacancy: Vacancy) {
        vacancy.id = vacancies.count()
        vacancies.add(vacancy)
    }

    override fun upgradeVacancy(id: Int, vacancy: Vacancy) {
        vacancies[id] = vacancies[id].copy(
            title = vacancy.title,
            description = vacancy.description,
            requirements = vacancy.requirements,
        )
    }

    override fun deleteVacancy(id: Int) {
        vacancies.removeAt(id)
    }

    override fun getAllVacancies(): List<Vacancy> {
        return vacancies
    }

    override fun getByKeyWordVacancy(keyWord: String): List<Vacancy> {
        return vacancies.filter {
            it.title!!.contains(keyWord, true)
        }
    }

    fun makeRegex(input: String): Regex {
        return Regex("(${input.split(" ").joinToString("|")})", options = setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
    }
    override fun getByFilter(vacancyFilter: Vacancy): List<Vacancy> {
        return vacancies.filter {
            it.title!!.contains(makeRegex(vacancyFilter.title!!))
            &&
            (
                (it.requirements?.age!! <= vacancyFilter.requirements?.age!!) ||
                (it.requirements?.experienceAge!! <= vacancyFilter.requirements?.experienceAge!!)
            )
            &&
            (
                it.requirements?.educationDegree!!.contains(vacancyFilter.requirements?.educationDegree!!)
            )
        }
    }
}

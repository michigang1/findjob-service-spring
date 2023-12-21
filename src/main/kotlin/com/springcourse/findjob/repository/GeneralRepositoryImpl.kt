package com.springcourse.findjob.repository

import com.springcourse.findjob.models.User
import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.models.VacancyDescription
import com.springcourse.findjob.models.VacancyRequirements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class GeneralRepositoryImpl(@Autowired private val jdbcTemplate: JdbcTemplate) : GeneralRepository {
    @Autowired
    var vacancies = mutableListOf<Vacancy>()
    override fun createVacancy(vacancy: Vacancy): Int {
        jdbcTemplate.update(
            "INSERT INTO vacancy (title, company, schedule, phoneNum, age, experienceAge, educationDegree, otherReqs) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            vacancy.title,
            vacancy.description?.company,
            vacancy.description?.schedule,
            vacancy.description?.phoneNum,
            vacancy.requirements?.age,
            vacancy.requirements?.experienceAge,
            vacancy.requirements?.educationDegree,
            vacancy.requirements?.otherReqs,
        )
        val id = jdbcTemplate.query<Int>("SELECT id FROM vacancy WHERE title = ?", arrayOf(vacancy.title)) { rs, _ ->
            rs.getInt("id")
        }.last()

        return id
    }

    override fun upgradeVacancy(id: Int, vacancy: Vacancy) {
        jdbcTemplate.update(
            "UPDATE vacancy SET title = ?, company = ?, schedule = ?, phoneNum = ?, age = ?, experienceAge = ?, educationDegree = ?, otherReqs = ? WHERE id = ?",
            vacancy.title,
            vacancy.description?.company,
            vacancy.description?.schedule,
            vacancy.description?.phoneNum,
            vacancy.requirements?.age,
            vacancy.requirements?.experienceAge,
            vacancy.requirements?.educationDegree,
            vacancy.requirements?.otherReqs,
            id,
        )
    }

    override fun deleteVacancy(id: Int) {
        jdbcTemplate.update(
            "DELETE FROM vacancy WHERE id = ?",
            id,
        )
    }

    override fun getAllVacancies(): List<Vacancy> {
        return jdbcTemplate.query(
            "SELECT * FROM vacancy",
        ) { rs, _ ->
            Vacancy(
                rs.getInt("id"),
                rs.getString("title"),
                VacancyDescription(
                    rs.getString("company"),
                    rs.getString("schedule"),
                    rs.getString("phoneNum"),
                ),
                VacancyRequirements(
                    rs.getInt("age"),
                    rs.getInt("experienceAge"),
                    rs.getString("educationDegree"),
                    rs.getString("otherReqs"),
                ),
            )
        }
    }

    override fun getByKeyWordVacancy(keyWord: String): List<Vacancy> {
        return jdbcTemplate.query(
            "SELECT * FROM vacancy WHERE title LIKE ?",
            arrayOf("%$keyWord%"),
        ) { rs, _ ->
            Vacancy(
                rs.getInt("id"),
                rs.getString("title"),
                VacancyDescription(
                    rs.getString("company"),
                    rs.getString("schedule"),
                    rs.getString("phoneNum"),
                ),
                VacancyRequirements(
                    rs.getInt("age"),
                    rs.getInt("experienceAge"),
                    rs.getString("educationDegree"),
                    rs.getString("otherReqs"),
                ),
            )
        }
    }

    fun makeRegex(input: String): Regex {
        return Regex("(${input.split(" ").joinToString("|")})", options = setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
    }
    override fun getByFilter(vacancyFilter: Vacancy): List<Vacancy> {
val regexTitle = makeRegex(vacancyFilter.title ?: "")
        val regexCompany = makeRegex(vacancyFilter.description?.company ?: "")
        val regexSchedule = makeRegex(vacancyFilter.description?.schedule ?: "")
        val regexPhoneNum = makeRegex(vacancyFilter.description?.phoneNum ?: "")
        val regexAge = makeRegex(vacancyFilter.requirements?.age.toString())
        val regexExperienceAge = makeRegex(vacancyFilter.requirements?.experienceAge.toString())
        val regexEducationDegree = makeRegex(vacancyFilter.requirements?.educationDegree ?: "")
        val regexOtherReqs = makeRegex(vacancyFilter.requirements?.otherReqs ?: "")
        return jdbcTemplate.query(
            "SELECT * FROM vacancy WHERE title ~ ? AND company ~ ? AND schedule ~ ? AND phoneNum ~ ? AND age ~ ? AND experienceAge ~ ? AND educationDegree ~ ? AND otherReqs ~ ?",
            arrayOf(
                regexTitle,
                regexCompany,
                regexSchedule,
                regexPhoneNum,
                regexAge,
                regexExperienceAge,
                regexEducationDegree,
                regexOtherReqs,
            ),
        ) { rs, _ ->
            Vacancy(
                rs.getInt("id"),
                rs.getString("title"),
                VacancyDescription(
                    rs.getString("company"),
                    rs.getString("schedule"),
                    rs.getString("phoneNum"),
                ),
                VacancyRequirements(
                    rs.getInt("age"),
                    rs.getInt("experienceAge"),
                    rs.getString("educationDegree"),
                    rs.getString("otherReqs"),
                ),
            )
        }
    }

    override fun getUserByName(name: String): User {
       return jdbcTemplate.query(
            "SELECT * FROM user WHERE username = ?",
            arrayOf(name),
        ) { rs, _ ->
            User(
                rs.getString("username"),
                rs.getBoolean("isWorker"),
            )
        }.first()
    }

    override fun getVacanciesByAge(age: Int): List<Vacancy> {
        return jdbcTemplate.query(
            "SELECT * FROM vacancy WHERE age = ?",
            arrayOf(age),
        ) { rs, _ ->
            Vacancy(
                rs.getInt("id"),
                rs.getString("title"),
                VacancyDescription(
                    rs.getString("company"),
                    rs.getString("schedule"),
                    rs.getString("phoneNum"),
                ),
                VacancyRequirements(
                    rs.getInt("age"),
                    rs.getInt("experienceAge"),
                    rs.getString("educationDegree"),
                    rs.getString("otherReqs"),
                ),
            )
        }
    }

}

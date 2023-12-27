package com.springcourse.findjob.repository

import com.springcourse.findjob.models.VacancyDto
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*


//class GeneralCrudRepositoryImpl() : GeneralCrudRepository {

//    @PersistenceContext
//    lateinit var entityManager: EntityManager
//
//    @Transactional
//    override fun save(vacancyDto: VacancyDto) {
//        entityManager.createNativeQuery("INSERT INTO description (id, company, schedule, phoneNum) VALUES (?, ?, ?, ?)")
//            .setParameter(1, vacancyDto.description.id)
//            .setParameter(2, vacancyDto.description.company)
//            .setParameter(3, vacancyDto.description.schedule)
//            .setParameter(4, vacancyDto.description.phoneNum)
//            .executeUpdate()
//
//        entityManager.createNativeQuery("INSERT INTO requirements (id, age, experienceAge, educationDegree, otherReqs) VALUES (?, ?, ?, ?, ?)")
//            .setParameter(1, vacancyDto.requirements.id)
//            .setParameter(2, vacancyDto.requirements.age)
//            .setParameter(3, vacancyDto.requirements.experienceAge)
//            .setParameter(4, vacancyDto.requirements.educationDegree)
//            .setParameter(5, vacancyDto.requirements.otherReqs)
//            .executeUpdate()
//
//        entityManager.createNativeQuery("INSERT INTO vacancy (id, title, descriptionId, requirementsId) VALUES (?, ?, ?, ?)")
//            .setParameter(1, vacancyDto.id)
//            .setParameter(2, vacancyDto.title)
//            .setParameter(3, vacancyDto.description.id)
//            .setParameter(4, vacancyDto.description.id)
//            .executeUpdate()
//
//    }


//    @Autowired
//    var vacancies = mutableListOf<Vacancy>()
//    override fun createVacancy(vacancy: Vacancy): Int {
//        jdbcTemplate.update(
//            "INSERT INTO vacancy (title, company, schedule, phoneNum, age, experienceAge, educationDegree, otherReqs) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
//            vacancy.title,
//            vacancy.description?.company,
//            vacancy.description?.schedule,
//            vacancy.description?.phoneNum,
//            vacancy.requirements?.age,
//            vacancy.requirements?.experienceAge,
//            vacancy.requirements?.educationDegree,
//            vacancy.requirements?.otherReqs,
//        )
//        val id = jdbcTemplate.query<Int>("SELECT id FROM vacancy WHERE title = ?", arrayOf(vacancy.title)) { rs, _ ->
//            rs.getInt("id")
//        }.last()
//
//    }
//
//    override fun upgradeVacancy(id: Int, vacancy: Vacancy) {
//        if (!existsById(id)) throw WrongPathVariableException()
//        jdbcTemplate.update(
//            "UPDATE vacancy SET title = ?, company = ?, schedule = ?, phoneNum = ?, age = ?, experienceAge = ?, educationDegree = ?, otherReqs = ? WHERE id = ?",
//            vacancy.title,
//            vacancy.descriptionId?.company,
//            vacancy.descriptionId?.schedule,
//            vacancy.descriptionId?.phoneNum,
//            vacancy.requirements?.age,
//            vacancy.requirements?.experienceAge,
//            vacancy.requirements?.educationDegree,
//            vacancy.requirements?.otherReqs,
//            id,
//        )
//    }
//
//    override fun deleteVacancy(id: Int) {
//        if (!existsById(id)) throw WrongPathVariableException()
//        jdbcTemplate.update(
//            "DELETE FROM vacancy WHERE id = ?",
//            id,
//        )
//    }
//
//    override fun getAllVacancies(): List<Vacancy> {
//        return jdbcTemplate.query(
//            "SELECT * FROM vacancy",
//        ) { rs, _ ->
//            Vacancy(
//                rs.getInt("id"),
//                rs.getString("title"),
//                VacancyDescription(
//                    rs.getString("company"),
//                    rs.getString("schedule"),
//                    rs.getString("phoneNum"),
//                ),
//                VacancyRequirements(
//                    rs.getInt("age"),
//                    rs.getInt("experienceAge"),
//                    rs.getString("educationDegree"),
//                    rs.getString("otherReqs"),
//                ),
//            )
//        }
//    }
//
//    override fun getByKeyWordVacancy(keyWord: String): List<Vacancy> {
//        return jdbcTemplate.query(
//            "SELECT * FROM vacancy WHERE title LIKE ?",
//            arrayOf("%$keyWord%"),
//        ) { rs, _ ->
//            Vacancy(
//                rs.getInt("id"),
//                rs.getString("title"),
//                VacancyDescription(
//                    rs.getString("company"),
//                    rs.getString("schedule"),
//                    rs.getString("phoneNum"),
//                ),
//                VacancyRequirements(
//                    rs.getInt("age"),
//                    rs.getInt("experienceAge"),
//                    rs.getString("educationDegree"),
//                    rs.getString("otherReqs"),
//                ),
//            )
//        }
//    }
//
//    fun makeRegex(input: String): Regex {
//        return Regex("(${input.split(" ").joinToString("|")})", options = setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))
//    }
//    override fun getByFilter(vacancyFilter: Vacancy): List<Vacancy> {
//val regexTitle = makeRegex(vacancyFilter.title ?: "")
//        val regexCompany = makeRegex(vacancyFilter.descriptionId?.company ?: "")
//        val regexSchedule = makeRegex(vacancyFilter.descriptionId?.schedule ?: "")
//        val regexPhoneNum = makeRegex(vacancyFilter.descriptionId?.phoneNum ?: "")
//        val regexAge = makeRegex(vacancyFilter.requirements?.age.toString())
//        val regexExperienceAge = makeRegex(vacancyFilter.requirements?.experienceAge.toString())
//        val regexEducationDegree = makeRegex(vacancyFilter.requirements?.educationDegree ?: "")
//        val regexOtherReqs = makeRegex(vacancyFilter.requirements?.otherReqs ?: "")
//        return jdbcTemplate.query(
//            "SELECT * FROM vacancy WHERE title ~ ? AND company ~ ? AND schedule ~ ? AND phoneNum ~ ? AND age ~ ? AND experienceAge ~ ? AND educationDegree ~ ? AND otherReqs ~ ?",
//            arrayOf(
//                regexTitle,
//                regexCompany,
//                regexSchedule,
//                regexPhoneNum,
//                regexAge,
//                regexExperienceAge,
//                regexEducationDegree,
//                regexOtherReqs,
//            ),
//        ) { rs, _ ->
//            Vacancy(
//                rs.getInt("id"),
//                rs.getString("title"),
//                VacancyDescription(
//                    rs.getString("company"),
//                    rs.getString("schedule"),
//                    rs.getString("phoneNum"),
//                ),
//                VacancyRequirements(
//                    rs.getInt("age"),
//                    rs.getInt("experienceAge"),
//                    rs.getString("educationDegree"),
//                    rs.getString("otherReqs"),
//                ),
//            )
//        }
//    }
//
//    override fun getUserByName(name: String): User {
//       return jdbcTemplate.query(
//            "SELECT * FROM user WHERE username = ?",
//            arrayOf(name),
//        ) { rs, _ ->
//            User(
//                rs.getString("username"),
//                rs.getBoolean("isWorker"),
//            )
//        }.first()
//    }
//
//    override fun getVacanciesByAge(age: Int): List<Vacancy> {
//        return jdbcTemplate.query(
//            "SELECT * FROM vacancy WHERE age = ?",
//            arrayOf(age),
//        ) { rs, _ ->
//            Vacancy(
//                rs.getInt("id"),
//                rs.getString("title"),
//                VacancyDescription(
//                    rs.getString("company"),
//                    rs.getString("schedule"),
//                    rs.getString("phoneNum"),
//                ),
//                VacancyRequirements(
//                    rs.getInt("age"),
//                    rs.getInt("experienceAge"),
//                    rs.getString("educationDegree"),
//                    rs.getString("otherReqs"),
//                ),
//            )
//        }
//    }
//
//    private fun existsById(id: Int): Boolean{
//        return jdbcTemplate.query(
//            "SELECT * FROM vacancy WHERE id = ?",
//            arrayOf(id),
//        ) { rs, _ ->
//            Vacancy(
//                rs.getInt("id"),
//                rs.getString("title"),
//                VacancyDescription(
//                    rs.getString("company"),
//                    rs.getString("schedule"),
//                    rs.getString("phoneNum"),
//                ),
//                VacancyRequirements(
//                    rs.getInt("age"),
//                    rs.getInt("experienceAge"),
//                    rs.getString("educationDegree"),
//                    rs.getString("otherReqs"),
//                ),
//            )
//        }.isNotEmpty()
//    }
//
//}

package com.springcourse.findjob.repository

import com.springcourse.findjob.models.Vacancy
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface GeneralCrudRepository: CrudRepository<Vacancy, Long> {

    fun save(vacancyDto: Vacancy)
    @Modifying
    @Query("update Vacancy v set v.title = ?2, v.company = ?3, v.schedule = ?4, v.phoneNum = ?5, v.age = ?6, v.experienceAge = ?7, v.educationDegree = ?8, v.otherReqs = ?9 where v.id = ?1")
    fun upgradeById(id: Int,
                    title: String,
                    company: String,
                    schedule: String,
                    phoneNum: String,
                    age: Int,
                    experienceAge: Int,
                    educationDegree: String,
                    otherReqs: String)

    @Modifying
    fun deleteById(id: Int)
    fun getAll(): List<Vacancy>
    fun getById(id: Int): Vacancy?


    fun getByTitleStartingWith(@Param("title")title: String): List<Vacancy>

    @Query("from Vacancy v where v.age=?1")
    fun getByAge(age: Int): List<Vacancy>
}
package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/vacancies")
abstract class GeneralController(private val generalService: GeneralService) {
    @GetMapping("/")
    @ResponseBody
    fun getAllVacancies() : List<Vacancy> {
        println("Listed all vacancies")
        return generalService.getAllVacancies()
    }
    abstract fun createVacancy(vacancy: Vacancy): Vacancy
    abstract fun upgradeVacancy(vacancy: Vacancy): Vacancy
    abstract fun deleteVacancy(id: Int) : Vacancy
    abstract fun getByKeyWordVacancy(keyWord: String) : List<Vacancy>
}
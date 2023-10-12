package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user/{username}/vacancies")
@RestController
class WorkerControllerImpl(@Autowired private val generalService: GeneralService) {
    @GetMapping("/")
    fun getAllVacancies(@PathVariable username: String) : List<Vacancy> {
        println("${username} listed all vacancies")
        return generalService.getAllVacancies()
    }
    @GetMapping("/search/")
    fun getByKeyWordVacancy(@PathVariable username: String, keyWord: String): List<Vacancy> {
        println("${username} invoked search by keyword")
        return generalService.getByKeyWordVacancy(keyWord)
    }
}
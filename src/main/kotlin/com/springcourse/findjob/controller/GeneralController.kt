package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class GeneralController(@Autowired private val generalService: GeneralService) {
    @GetMapping("/vacancies")
    @ResponseBody
    fun getAllVacancies() = generalService.getAllVacancies()

    @PostMapping("/vacancies")
    fun createVacancy(@RequestBody vacancy: Vacancy) = generalService.createVacancy(vacancy)

    @PutMapping("/vacancies/{id}")
    @ResponseBody
    fun upgradeVacancy(@PathVariable("id") id: Int, @RequestBody vacancy: Vacancy) = generalService.upgradeVacancy(id, vacancy)

    @DeleteMapping("/vacancies/{id}")
    fun deleteVacancy(@PathVariable("id") id: Int) = generalService.deleteVacancy(id)

    @GetMapping("/vacancies/search")
    fun getByKeyWordVacancy(@RequestParam("keyWord") keyWord: String) = generalService.getByKeyWordVacancy(keyWord)
}

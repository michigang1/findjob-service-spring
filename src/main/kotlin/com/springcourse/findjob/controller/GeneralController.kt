package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vacancies")
class GeneralController(@Autowired private val generalService: GeneralService) {
    //get list of vacancies
    @GetMapping("/")
    @ResponseBody
    fun getAllVacancies() : List<Vacancy> {
        return generalService.getAllVacancies()
    }

    @PostMapping("/")
    @ResponseBody
    fun createVacancy(@RequestParam("title", required = false) title: String?,
                      @RequestParam("description", required = false) description: String?,
                      @RequestParam("requirements", required = false) requirements: String?,
                      @RequestBody(required = false) vacancy: Vacancy?): Vacancy {
        if (vacancy != null)
            generalService.createVacancy(vacancy)
        else if (title != null && description != null && requirements != null) {
            val vac = Vacancy(title, description, requirements)
            generalService.createVacancy(vac)
        }
        println("Added new vacancy")
        return getAllVacancies().last()
    }
    @PutMapping("/")
    @ResponseBody
    fun upgradeVacancy(@RequestParam("id") id: Int,
                       @RequestParam("requirements", required = false) requirements: String?,
                       @RequestParam("description", required = false) description: String?,
                       @RequestParam("title", required = false) title: String?,
                       @RequestBody(required = false) vacancy: Vacancy?): Vacancy {
        if (vacancy != null)
            generalService.upgradeVacancy(id, vacancy)
        else if (title != null || description != null || requirements != null) {
            val vac = Vacancy(title ?: getAllVacancies().get(id).title,
                    description ?: getAllVacancies().get(id).description,
                    requirements ?: getAllVacancies().get(id).requirements)
            generalService.upgradeVacancy(id, vac)
        }
        println("Changed vacancy with id=$id")
        return getAllVacancies().get(id)
    }
    //delete vacancy
    @DeleteMapping("/")
    @ResponseBody
    fun deleteVacancy(@RequestParam("id") id: Int) : Vacancy {
        val vacToDel = generalService.getAllVacancies().get(id)
        generalService.deleteVacancy(id)
        println("Deleted vacancy with id=$id")
        return vacToDel
    }
    //find vacancies by keyword
    @GetMapping("/search/")
    @ResponseBody
    fun getByKeyWordVacancy(@RequestParam("keyWord") keyWord: String) : List<Vacancy> {
        println("Invoked search by keyword")
        return generalService.getByKeyWordVacancy(keyWord)
    }
}

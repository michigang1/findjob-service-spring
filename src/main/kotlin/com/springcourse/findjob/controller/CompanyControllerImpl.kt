package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.web.bind.annotation.*

@RequestMapping("/company/{company}/vacancies")
@RestController
class CompanyControllerImpl(private val generalService: GeneralService) {
    @GetMapping("/")
    fun getCompanyVacancies(@PathVariable company: String) : List<Vacancy> {
        println("${company} listed all own vacancies")
        return generalService.getCompanyVacancies(company)
    }
    @PostMapping("/")
    fun createVacancy(@PathVariable company: String, @RequestBody vacancy: Vacancy): Vacancy {
        vacancy.description.company = company
        generalService.createVacancy(vacancy)
        println("${company} added new vacancy")
        return generalService.getAllVacancies().last()
    }
    @PutMapping("/")
    fun upgradeVacancy(@PathVariable company: String, @RequestBody vacancy: Vacancy): Vacancy {
        vacancy.description.company = company
        generalService.upgradeVacancy(vacancy.id, vacancy)
        println("${company} changed vacancy with id=${vacancy.id}")
        return generalService.getAllVacancies().get(vacancy.id)
    }
    //delete vacancy
    @DeleteMapping("/")
    fun deleteVacancy(@PathVariable company: String, @RequestParam("id") id: Int) : Vacancy {
        val vacToDel = generalService.getAllVacancies().get(id)
        generalService.deleteVacancy(id)
        println("${company} deleted vacancy with id=$id")
        return vacToDel
    }
}
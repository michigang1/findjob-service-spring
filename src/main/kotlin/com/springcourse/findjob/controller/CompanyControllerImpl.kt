package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.web.bind.annotation.*

@RequestMapping("/company/vacancies")
@RestController
class CompanyControllerImpl(private val generalService: GeneralService) : GeneralController(generalService) {

    @PostMapping("/")
    @ResponseBody
    override fun createVacancy(@RequestBody vacancy: Vacancy): Vacancy {
        generalService.createVacancy(vacancy)
        println("Added new vacancy")
        return generalService.getAllVacancies().last()
    }
    @PutMapping("/")
    @ResponseBody
    override fun upgradeVacancy(@RequestBody vacancy: Vacancy): Vacancy {
        generalService.upgradeVacancy(vacancy.id, vacancy)
        println("Changed vacancy with id=${vacancy.id}")
        return generalService.getAllVacancies().get(vacancy.id)
    }
    //delete vacancy
    @DeleteMapping("/")
    @ResponseBody
    override fun deleteVacancy(@RequestParam("id") id: Int) : Vacancy {
        val vacToDel = getAllVacancies().get(id)
        generalService.deleteVacancy(id)
        println("Deleted vacancy with id=$id")
        return vacToDel
    }
    override fun getByKeyWordVacancy(keyWord: String): List<Vacancy> {
        TODO("Not yet implemented")
    }
}
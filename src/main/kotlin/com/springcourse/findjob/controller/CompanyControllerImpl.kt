package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Value
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RequestMapping("/company/{company}/vacancies")
@RestController
class CompanyControllerImpl(private val generalService: GeneralService) {

    @GetMapping("/")
    fun getCompanyVacancies(@PathVariable("company") company: String): ModelAndView {
        val vacancies = generalService.getCompanyVacancies(company)
        val mav = ModelAndView("company")
        mav.addObject("vacancies", vacancies)
        println("$company listed all own vacancies")
        println("${vacancies.map { it.description }}")
        return mav
    }

    @PostMapping("/create")
    fun createVacancy(@PathVariable company: String, @RequestBody vacancy: Vacancy, model: Model): Vacancy {
        vacancy.description.company = company
        generalService.createVacancy(vacancy)
        println("$company added new vacancy")

        return generalService.getAllVacancies().last()
    }

    @PostMapping("/save")
    fun upgradeVacancy(
        @PathVariable("company") company: String,
        @RequestParam("vacancyId") id: Int,
        @ModelAttribute vacancy: Vacancy,
    ): String {
        generalService.upgradeVacancy(id, vacancy)
        println("$company changed vacancy with id=${vacancy.id}")
        return "redirect:/"
    }

    @GetMapping("/edit")
    fun showEditVacancyView(@PathVariable("company") company: String, @RequestParam("vacancyId") id: Int): ModelAndView {
        val mav = ModelAndView("edit")
        val vacancy = generalService.getVacancyById(id)
        mav.addObject("vacancyEdit", vacancy)
        return mav
    }

    // delete vacancy
    @DeleteMapping("/delete")
    fun deleteVacancy(@PathVariable("company") company: String, @RequestParam("vacancyId") id: Int): ModelAndView {
        val mav = ModelAndView("redirect:http://localhost:8080/company/$company/vacancies/")
        mav.addObject("vacancies", generalService.deleteVacancy(id))
        println("$company deleted vacancy with id=$id")
        return mav
    }
}

package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.models.VacancyDescription
import com.springcourse.findjob.models.VacancyRequirements
import com.springcourse.findjob.service.GeneralService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RequestMapping("/company/{company}/vacancies")
@Controller
class CompanyControllerImpl(private val generalService: GeneralService) {

    @GetMapping("/")
    fun getCompanyVacancies(@PathVariable("company") company: String): ModelAndView {
        val vacancies = generalService.getCompanyVacancies(company)
        val mav = ModelAndView("company")
        mav.addObject("vacancies", vacancies)
        //println("${vacancies.map { it.description }}")
        return mav
    }

    @PostMapping("/create")
    fun createVacancy(@PathVariable company: String, @ModelAttribute("vacancyAdd") vacancy: Vacancy, model: Model): ModelAndView {
        val view = ModelAndView("redirect:/company/$company/vacancies/")
        generalService.createVacancy(vacancy)
        return view
    }

    @GetMapping("/add")
    fun showCreateVacancyView(@PathVariable("company") company: String): ModelAndView {
        val mav = ModelAndView("create")
        mav.addObject(
            "vacancyAdd",
            Vacancy(
                id = 0,
                description = VacancyDescription(company = company),
                requirements = VacancyRequirements(),
            ),
        )
        return mav
    }

    @PostMapping("/save")
    fun upgradeVacancy(
        @PathVariable("company") company: String,
        @ModelAttribute("vacancyEdit") vacancyEdit: Vacancy,
    ): ModelAndView {
        val view = ModelAndView("redirect:http://localhost:8080/company/$company/vacancies/")
        generalService.upgradeVacancy(vacancyEdit.id, vacancyEdit)
        return view
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
        return mav
    }
}

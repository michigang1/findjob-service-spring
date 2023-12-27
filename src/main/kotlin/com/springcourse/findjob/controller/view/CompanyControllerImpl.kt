package com.springcourse.findjob.controller.view

import com.springcourse.findjob.models.VacancyDto
import com.springcourse.findjob.models.VacancyDescriptionDto
import com.springcourse.findjob.models.VacancyRequirementsDto
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
        return mav
    }

    @PostMapping("/create")
    fun createVacancy(@PathVariable company: String, @ModelAttribute("vacancyAdd") vacancyDto: VacancyDto, model: Model): ModelAndView {
        val view = ModelAndView("redirect:/company/$company/vacancies/")
        generalService.createVacancy(vacancyDto)
        return view
    }

    @GetMapping("/add")
    fun showCreateVacancyView(@PathVariable("company") company: String): ModelAndView {
        val mav = ModelAndView("create")
        mav.addObject(
            "vacancyAdd",
            VacancyDto(
                id = 0,
                description = VacancyDescriptionDto(company = company),
                requirements = VacancyRequirementsDto(),
            ),
        )
        return mav
    }

    @PostMapping("/save")
    fun upgradeVacancy(
        @PathVariable("company") company: String,
        @ModelAttribute("vacancyEdit") vacancyDtoEdit: VacancyDto,
    ): ModelAndView {
        val view = ModelAndView("redirect:http://localhost:8080/company/$company/vacancies/")
        generalService.upgradeVacancy(vacancyDtoEdit.id, vacancyDtoEdit)
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

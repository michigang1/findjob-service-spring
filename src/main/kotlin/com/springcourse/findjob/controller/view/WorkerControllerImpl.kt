package com.springcourse.findjob.controller.view

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.models.VacancyDescription
import com.springcourse.findjob.models.VacancyRequirements
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RequestMapping("/user/{username}/vacancies")
@Controller
class WorkerControllerImpl(@Autowired private val generalService: GeneralService) {

    @GetMapping("/list")
    fun getPageVacancies(
        @PathVariable("username") username: String,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") pageSize: Int
    ): ModelAndView {
        val paginated = paginate(pageSize, generalService.getAllVacancies())
        val mav = ModelAndView("worker")
        mav.addObject("allVacancies", paginated[page])
        mav.addObject("pageCount", paginated.count())
        mav.addObject("pageSize", pageSize)
        mav.addObject("vacancyFilter", Vacancy(description = VacancyDescription(), requirements = VacancyRequirements()))
        return mav
    }

    @GetMapping("/search")
    fun getByKeyWordVacancy(
        @PathVariable("username") username: String,
        @RequestParam("keywords", required = false) keyWord: String,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") pageSize: Int
    ): ModelAndView {
        val mav = ModelAndView("worker")
        mav.addObject("allVacancies", generalService.getByKeyWordVacancy(keyWord))
        mav.addObject("pageCount", 0)
        mav.addObject("pageSize", pageSize)
        mav.addObject("vacancyFilter", Vacancy(description = VacancyDescription(), requirements = VacancyRequirements()))
        return mav
    }

    @PostMapping("/filter")
    fun getByFilter(
        @PathVariable("username") username: String,
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "10") pageSize: Int,
        @ModelAttribute("vacancyFilter") vacancyFilter: Vacancy
    ): ModelAndView {
        println(vacancyFilter)
        val mav = ModelAndView("worker")
        mav.addObject("allVacancies", generalService.getByFilter(vacancyFilter))
        mav.addObject("pageCount", 0)
        mav.addObject("pageSize", pageSize)
        return mav
    }

    fun paginate(pageSize: Int, vacancies: List<Vacancy>) : List<List<Vacancy>> {
        return vacancies.chunked(pageSize)
    }
}

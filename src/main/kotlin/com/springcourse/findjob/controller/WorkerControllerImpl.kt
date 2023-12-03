package com.springcourse.findjob.controller

import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RequestMapping("/user/{username}/vacancies")
@Controller
class WorkerControllerImpl(@Autowired private val generalService: GeneralService) {
    @GetMapping("/")
    fun getAllVacancies(@PathVariable username: String): ModelAndView {
        val mav = ModelAndView("worker")
        mav.addObject("allVacancies", generalService.getAllVacancies())
        println("$username listed all vacancies")
        return mav
    }

    @GetMapping("/search")
    fun getByKeyWordVacancy(@PathVariable username: String, @RequestParam(name = "keywords", required = false) keyWord: String): ModelAndView {
        println("$username invoked search by keyword")
        val mav = ModelAndView("worker")
        mav.addObject("allVacancies", generalService.getByKeyWordVacancy(keyWord))
        println("$username listed all vacancies")
        return mav
    }
}

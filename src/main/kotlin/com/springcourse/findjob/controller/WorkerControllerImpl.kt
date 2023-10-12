package com.springcourse.findjob.controller

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user/vacancies")
@RestController
class WorkerControllerImpl(@Autowired private val generalService: GeneralService) : GeneralController(generalService) {
    @GetMapping("/search/")
    @ResponseBody
    override fun getByKeyWordVacancy(keyWord: String): List<Vacancy> {
        println("User <placeholder> invoked search by keyword")
        return generalService.getByKeyWordVacancy(keyWord)
    }

    override fun createVacancy(vacancy: Vacancy): Vacancy {
        TODO("Not yet implemented")
    }

    override fun deleteVacancy(id: Int): Vacancy {
        TODO("Not yet implemented")
    }

    override fun upgradeVacancy(vacancy: Vacancy): Vacancy {
        TODO("Not yet implemented")
    }
}
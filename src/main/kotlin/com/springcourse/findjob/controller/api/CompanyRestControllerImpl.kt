package com.springcourse.findjob.controller.api

import com.springcourse.findjob.models.VacancyDto
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/company/{company}/vacancies")
class CompanyRestControllerImpl(@Autowired private val service: GeneralService) : CompanyRestController {
    @GetMapping("/")
    override fun getCompanyVacancies(@PathVariable("company") company: String) = ResponseEntity.ok(service.getCompanyVacancies(company))

    @GetMapping("/{id}")
    override fun getVacancyById(@PathVariable("id") id: Int) = ResponseEntity.ok(service.getVacancyById(id))

    @PostMapping("/create")
    override fun createVacancy(
        @RequestBody @Validated
        vacancyDto: VacancyDto,
        @PathVariable("company") company: String,
    ) {
       service.createVacancy(vacancyDto)
    }

    @PutMapping("/upgrade/{id}")
    override fun upgradeVacancy(@PathVariable("id") id: Int, @RequestBody @Validated vacancyDto: VacancyDto) {
        ResponseEntity.ok(service.upgradeVacancy(id, vacancyDto))
    }

    @DeleteMapping("/delete/{id}")
    override fun deleteVacancy(@PathVariable("id") id: Int, @PathVariable("company") company: String) {
        ResponseEntity.ok(service.deleteVacancy(id))
    }
}

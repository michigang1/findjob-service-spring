package com.springcourse.findjob.controller.api

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/company/{company}/vacancies")
class CompanyRestControllerImpl(@Autowired private val service: GeneralService) : CompanyRestController {
    @GetMapping("/")
    override fun getCompanyVacancies(@PathVariable("company") company: String): ResponseEntity<Any> {
        val found = service.getCompanyVacancies(company)
        return if (found.any()) ResponseEntity.ok(found) else ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}")
    override fun getVacancyById(@PathVariable("id") id: Int): ResponseEntity<Any> {
        val found: Any;
        try {
            found = service.getVacancyById(id)
            return ResponseEntity.ok(found)
        } catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/create")
    override fun createVacancy(
        @RequestBody @Validated
        vacancy: Vacancy,
        @PathVariable("company") company: String,
    ): ResponseEntity<Int> {
       return ResponseEntity.ok(service.createVacancy(vacancy))
    }

    @PutMapping("/upgrade/{id}")
    override fun upgradeVacancy(@PathVariable("id") id: Int, @RequestBody @Validated vacancy: Vacancy) {
        ResponseEntity.ok(service.upgradeVacancy(id, vacancy))
    }

    @DeleteMapping("/delete/{id}")
    override fun deleteVacancy(@PathVariable("id") id: Int, @PathVariable("company") company: String) {
        ResponseEntity.ok(service.deleteVacancy(id))
    }
}

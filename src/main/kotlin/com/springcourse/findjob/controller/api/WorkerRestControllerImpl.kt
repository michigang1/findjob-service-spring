package com.springcourse.findjob.controller.api

import com.springcourse.findjob.service.GeneralService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user/{username}/vacancies")
class WorkerRestControllerImpl(@Autowired private val service: GeneralService) : WorkerRestController {
    @GetMapping("/")
    override fun getAllVacancies() = ResponseEntity.ok(service.getAllVacancies())

    @GetMapping("/search")
    override fun getAllVacanciesByKeyword(@RequestParam("keywords", required = false) keyword: String) = ResponseEntity.ok(service.getByKeyWordVacancy(keyword))

    @GetMapping("/searchByAge")
    override fun getAllVacanciesByAge(@RequestParam("age", required = false) age: Int): ResponseEntity<Any> {
        val found = service.getVacanciesByAge(age)
        return if (found.any()) ResponseEntity.ok(found) else ResponseEntity.badRequest().build()
    }
}

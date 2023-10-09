package com.springcourse.findjob.service

import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.repository.GeneralRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GeneralServiceImpl(@Autowired private val generalRepository: GeneralRepository) : GeneralService {
    override fun createVacancy(vacancy: Vacancy) = generalRepository.createVacancy(vacancy)

    override fun upgradeVacancy(id: Int, vacancy: Vacancy) = generalRepository.upgradeVacancy(id, vacancy)

    override fun deleteVacancy(id: Int) = generalRepository.deleteVacancy(id)

    override fun getAllVacancies() = generalRepository.getAllVacancies()

    override fun getByKeyWordVacancy(keyWord: String) = generalRepository.getByKeyWordVacancy(keyWord)
}

package com.springcourse.findjob.service

import com.springcourse.findjob.expections.XssVulnerableStringException
import com.springcourse.findjob.models.User
import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.repository.GeneralRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class GeneralServiceImpl(@Autowired private val generalRepository: GeneralRepository) : GeneralService {

    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED)
    override fun createVacancy(vacancy: Vacancy): Int {
        vacancy.checkForValidity()
        return generalRepository.createVacancy(vacancy)
    }

    override fun upgradeVacancy(id: Int, vacancy: Vacancy): Int {
        vacancy.checkForValidity()
        return generalRepository.upgradeVacancy(id, vacancy)
    }

    override fun deleteVacancy(id: Int) = generalRepository.deleteVacancy(id)

    override fun getAllVacancies() = generalRepository.getAllVacancies()
    override fun getCompanyVacancies(name: String): List<Vacancy> {
        return generalRepository.getAllVacancies().filter {
            it.description?.company?.lowercase() == name.lowercase()
        }
    }
    override fun getVacancyById(id: Int): Vacancy {
        return generalRepository.getAllVacancies().find {
            it.id == id
        } ?: throw Exception("Vacancy with id=$id not found")
    }

    override fun getByKeyWordVacancy(keyWord: String): List<Vacancy> {
        if (!keyWord.contains(regexString)) throw XssVulnerableStringException()
        return generalRepository.getByKeyWordVacancy(keyWord)
    }
    override fun getByFilter(vacancyFilter: Vacancy): List<Vacancy> {
        return generalRepository.getByFilter(vacancyFilter)
    }
    override fun getUserByName(name: String): User {
        return generalRepository.getUserByName(name)
    }

    override fun getVacanciesByAge(age: Int): List<Vacancy> {
        return generalRepository.getVacanciesByAge(age)
    }

    fun Vacancy.checkForValidity() {
        if (!this.title!!.contains(regexString)) throw XssVulnerableStringException()
        if (this.description?.company?.contains(regexString) == false) throw XssVulnerableStringException()
        if (this.description?.phoneNum?.contains(regexString) == false) throw XssVulnerableStringException()
        if (this.description?.schedule?.contains(regexString) == false) throw XssVulnerableStringException()
        if (this.requirements?.educationDegree?.contains(regexString) == false) throw XssVulnerableStringException()
        if (this.requirements?.otherReqs?.contains(regexString) == false) throw XssVulnerableStringException()
    }



    companion object {
        val regexString = Regex("^[a-zA-Z0-9 .,?!@#\$%^&*()_+-=;:'\"|\\\\/]*\$")
    }
}

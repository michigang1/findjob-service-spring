package com.springcourse.findjob.service

import com.springcourse.findjob.expections.*
import com.springcourse.findjob.models.Vacancy
import com.springcourse.findjob.models.VacancyDescriptionDto
import com.springcourse.findjob.models.VacancyDto
import com.springcourse.findjob.models.VacancyRequirementsDto
import com.springcourse.findjob.repository.GeneralCrudRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class GeneralServiceImpl(private val generalRepository: GeneralCrudRepository) : GeneralService {

    @Transactional( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED)
    override fun createVacancy(vacancyDto: VacancyDto) {
        vacancyDto.checkForValidity()
        generalRepository.save(vacancyDto.toVacancy())
    }

    @Transactional
    override fun upgradeVacancy(id: Int, vacancyDto: VacancyDto) {
        if(generalRepository.getById(id)==null) throw NoDataFoundByIdException()
        vacancyDto.checkForValidity()
        val vacancy = vacancyDto.toVacancy()
        generalRepository.upgradeById(id, vacancy.title, vacancy.company, vacancy.schedule!!, vacancy.phoneNum!!, vacancy.age!!, vacancy.experienceAge!!, vacancy.educationDegree!!,vacancy.otherReqs!!)
    }

    @Transactional
    override fun deleteVacancy(id: Int) {
        if(generalRepository.getById(id)==null) throw NoDataFoundByIdException()
        generalRepository.deleteById(id)
    }

    override fun getAllVacancies() = generalRepository.getAll().map { it.toVacancyDto() }
    override fun getCompanyVacancies(name: String): List<VacancyDto> {
        val list = generalRepository.getAll().map { it.toVacancyDto() }.filter {
            it.description?.company?.lowercase() == name.lowercase()
        }
        if(list.isEmpty()) throw WrongPathVariableException()
        return list
    }
    override fun getVacancyById(id: Int): VacancyDto {
        val vacancy = generalRepository.getById(id) ?: throw NoDataFoundByIdException()
        return vacancy.toVacancyDto()
    }

    override fun getByKeyWordVacancy(keyWord: String): List<VacancyDto> {
        if (!keyWord.contains(regexString)) throw XssVulnerableStringException()
        if (generalRepository.findByTitleContaining(keyWord).isEmpty()) throw WrongRequestParamException()
        return generalRepository.findByTitleContaining(keyWord).map { it.toVacancyDto() }
    }
    override fun getByFilter(vacancyDtoFilter: VacancyDto): List<VacancyDto> {
        val list =  generalRepository.getAll()

        val regexTitle = vacancyDtoFilter.title
        val regexCompany = vacancyDtoFilter.description?.company ?: ""
        val regexSchedule = vacancyDtoFilter.description?.schedule ?: ""
        val regexPhoneNum = vacancyDtoFilter.description?.phoneNum ?: ""
        val regexAge = vacancyDtoFilter.requirements?.age ?: 0
        val regexExperienceAge = vacancyDtoFilter.requirements?.experienceAge ?: 0
        val regexEducationDegree = vacancyDtoFilter.requirements?.educationDegree ?: ""
        val regexOtherReqs = vacancyDtoFilter.requirements?.otherReqs ?: ""

        val result = list.map {it.toVacancyDto()}.filter {
            (it.title == regexTitle.toString()) &&
                    (it.description?.company  == regexCompany) &&
                    (it.description.schedule == regexSchedule) &&
                    (it.description.phoneNum == regexPhoneNum) &&
                    (it.requirements?.age == regexAge) &&
                    (it.requirements?.experienceAge == regexExperienceAge) &&
                    (it.requirements?.educationDegree == regexEducationDegree) &&
                    (it.requirements?.otherReqs == regexOtherReqs)

        }
        return result
    }

    override fun getVacanciesByAge(age: Int): List<VacancyDto> {
        if (generalRepository.getByAge(age).isEmpty()) throw WrongRequestParamException()
        return generalRepository.getByAge(age).map { it.toVacancyDto() }
    }

    fun VacancyDto.checkForValidity() {
        if (!title.contains(regexString)) throw XssVulnerableStringException()
        if (description?.company?.contains(regexString) == false) throw XssVulnerableStringException()
        if (description?.phoneNum?.contains(regexString) == false) throw XssVulnerableStringException()
        if (description?.schedule?.contains(regexString) == false) throw XssVulnerableStringException()
        if (requirements?.educationDegree?.contains(regexString) == false) throw XssVulnerableStringException()
        if (requirements?.otherReqs?.contains(regexString) == false) throw XssVulnerableStringException()
        if (title == null || description == null || requirements == null ) throw NullableStringException()
    }

    fun VacancyDto.toVacancy() = Vacancy(
        id = id,
        title = title,
        company = description?.company ?: "",
        schedule = description?.schedule ?: "",
        phoneNum = description?.phoneNum ?: "",
        age = requirements?.age ?: 0,
        experienceAge = requirements?.experienceAge ?: 0,
        educationDegree = requirements?.educationDegree ?: "",
        otherReqs = requirements?.educationDegree ?: ""
    )

    fun Vacancy.toVacancyDto() = VacancyDto(
        id = id,
        title = title,
        VacancyDescriptionDto(company,schedule,phoneNum),
        VacancyRequirementsDto(age, experienceAge, educationDegree, otherReqs)
    )



    companion object {
        val regexString = Regex("^[a-zA-Z0-9 .,?!@#\$%^&*()_+-=;:'\"|\\\\/]*\$")
    }
}

package com.springcourse.findjob.models

//@Entity
//@Table(name = "vacancy", schema = "findjob")
//@NamedNativeQuery(
//    name = "Vacancy.getById",
//    query = "SELECT * FROM vacancy WHERE id = ?",
//    resultClass = Vacancy::class
//)
//
//@NamedNativeQuery(
//    name = "Vacancy.getAll",
//    query = "SELECT * FROM vacancy",
//    resultClass = Vacancy::class
//)

data class VacancyDto(

    val id: Int = 0,

    val title: String = "",

    val description: VacancyDescriptionDto? = VacancyDescriptionDto(),


    var requirements: VacancyRequirementsDto? = VacancyRequirementsDto()
) {

}

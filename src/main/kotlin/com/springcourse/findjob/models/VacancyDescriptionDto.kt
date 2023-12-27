package com.springcourse.findjob.models


data class VacancyDescriptionDto(
    var company: String = "",
    var schedule: String? = "",
    var phoneNum: String? = "",
)
package com.springcourse.findjob.models

data class VacancyRequirementsDto(
    var age: Int? = 0,
    var experienceAge: Int? = 0,
    var educationDegree: String? = "",
    var otherReqs: String? = "",
)

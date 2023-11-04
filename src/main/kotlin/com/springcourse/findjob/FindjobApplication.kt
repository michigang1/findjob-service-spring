package com.springcourse.findjob

import com.springcourse.findjob.models.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.*

@SpringBootApplication
class FindjobApplication {
    // singleton bean (repo stub)
    @Primary
    @Bean
    fun initialVacancies(): List<Vacancy> {
        val list = mutableListOf<Vacancy>()
        list.add(
            Vacancy(
                list.count(),
                "Java developer",
                VacancyDescription(company = "oracle", phoneNum = "88005553535"),
                VacancyRequirements(educationDegree = "Bachelor of Software Engineering"),
            ),
        )
        list.add(
            Vacancy(
                list.count(),
                "Unity developer",
                VacancyDescription(company = "Unity", phoneNum = "88005553535", schedule = "Full time"),
                VacancyRequirements(),
            ),
        )
        list.add(
            Vacancy(
                list.count(),
                "Waiter",
                VacancyDescription(company = "Clode Monet"),
                VacancyRequirements(),
            ),
        )
        return list
    }

    // prototype bean creator
    @Bean
    @Scope(value = "prototype")
    fun createNewUser(@Value("none") name: String, @Value("false") isWorker: Boolean) = User(name, isWorker)
}
fun main(args: Array<String>) {
    val ctx = runApplication<FindjobApplication>(*args)
/*	for (elem in ctx.beanDefinitionNames)
		println(elem)*/
    println("List of 2 users, they should have different hashcode")
    val user1 = ctx.getBean("createNewUser", "Unity", false) as User
    val user2 = ctx.getBean("createNewUser", "Michael", true) as User
    println("User 1: ${user1.username}. Is worker: ${user1.isWorker}. Hashcode: ${user1.hashCode()}")
    println("User 2: ${user2.username}. Is worker: ${user2.isWorker}. Hashcode: ${user2.hashCode()}")
    println("Now we try to get 2 'instances' of repo stub, they should have same hashcode")
    val repo1 = ctx.getBean("initialVacancies")
    val repo2 = ctx.getBean("initialVacancies")
    println("Repo '1' hashcode: ${repo1.hashCode()}")
    println("Repo '2' hashcode: ${repo2.hashCode()}")
}

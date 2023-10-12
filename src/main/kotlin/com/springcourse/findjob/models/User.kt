package com.springcourse.findjob.models

import org.springframework.beans.factory.annotation.Autowired

data class User(
    val username: String,
    val isWorker: Boolean,
)

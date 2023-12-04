package com.springcourse.findjob.expections

class WrongPathVariableException : RuntimeException() {
    override val message: String?
        get() = "Wrong path variable"
}

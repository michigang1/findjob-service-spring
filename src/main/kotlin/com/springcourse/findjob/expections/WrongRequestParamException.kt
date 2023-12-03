package com.springcourse.findjob.expections

class WrongRequestParamException : RuntimeException() {
    override val message: String?
        get() = "Wrong request param"
}

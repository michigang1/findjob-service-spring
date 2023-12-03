package com.springcourse.findjob.expections

class XssVulnerableStringException : Exception() {
    override val message = "Wrong data with special symbols"
}

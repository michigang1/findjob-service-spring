package com.springcourse.findjob.expections

class XssVulnerableStringExpection : Exception(){
    override val message = "Wrong data with special symbols"
}

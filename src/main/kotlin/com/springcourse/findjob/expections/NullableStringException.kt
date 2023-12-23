package com.springcourse.findjob.expections

class NullableStringException: NullPointerException() {
    override val message: String?
        get() = "String is null"
}
package com.springcourse.findjob.expections

class NoDataFoundByIdException: RuntimeException() {
    override val message = "Data is not found by this ID"
}
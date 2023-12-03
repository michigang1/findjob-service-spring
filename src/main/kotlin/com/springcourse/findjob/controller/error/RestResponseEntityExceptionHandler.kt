package com.springcourse.findjob.controller.error

import com.springcourse.findjob.expections.WrongPathVariableException
import com.springcourse.findjob.expections.XssVulnerableStringException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Controller
@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    fun handleIllegalArgumentException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        logger.error("Request: ${request.contextPath} raised " + ex)
        val bodyOfResponse = "This should be application specific"
        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.CONFLICT,
            request,
        )
    }

    @ExceptionHandler(value = [WrongPathVariableException::class])
    fun handleWrongPathVariableException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        logger.error("Request: ${request.contextPath} raised " + ex)
        val bodyOfResponse = "Wrong path variable"
        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.NOT_FOUND,
            request,
        )
    }

    @ExceptionHandler(value = [RuntimeException::class])
    fun handleOtherException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        logger.error("Request: ${request.contextPath} raised " + ex)
        val bodyOfResponse = "Something went wrong"
        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request,
        )
    }

    @ExceptionHandler(value = [XssVulnerableStringException::class])
    fun handleXssVulnerableStringException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        logger.error("Request: ${request.contextPath} raised " + ex)
        val bodyOfResponse = "XSS Vulnerable String"
        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request,
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleError(req: HttpServletRequest, ex: Exception): ModelAndView {
        logger.error("Request: " + req.requestURL + " raised " + ex)
        val mav = ModelAndView("error")
        mav.addObject("exception", ex)
        mav.addObject("url", req.requestURL)
        return mav
    }
}

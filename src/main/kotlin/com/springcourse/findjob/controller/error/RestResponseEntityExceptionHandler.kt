package com.springcourse.findjob.controller.error

import com.springcourse.findjob.expections.WrongPathVariableException
import com.springcourse.findjob.expections.WrongRequestParamException
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

    private fun prepareResponse(ex: RuntimeException, request: WebRequest, bodyOfResponse: String, status: HttpStatus): ResponseEntity<Any>? {
        logger.error("Request: ${request.contextPath} raised " + ex)
        return handleExceptionInternal(
            ex,
            bodyOfResponse,
            HttpHeaders(),
            status,
            request,
        )
    }

    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    fun handleIllegalArgumentException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        return prepareResponse(ex, request, "This should be application specific", HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [WrongPathVariableException::class])
    fun handleWrongPathVariableException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        return prepareResponse(ex, request, "Wrong path variable", HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [WrongRequestParamException::class])
    fun handleWrongRequestParamException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        return prepareResponse(ex, request, "Wrong request parameter", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [RuntimeException::class])
    fun handleOtherException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        return prepareResponse(ex, request, "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [XssVulnerableStringException::class])
    fun handleXssVulnerableStringException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        return prepareResponse(ex, request, "XSS Vulnerable String", HttpStatus.BAD_REQUEST)
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

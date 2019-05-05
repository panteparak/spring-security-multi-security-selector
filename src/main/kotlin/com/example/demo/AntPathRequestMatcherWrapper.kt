package com.example.demo

import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest

abstract class AntPathRequestMatcherWrapper : RequestMatcher {
    private val delagate: AntPathRequestMatcher

    constructor(path: String){
        this.delagate = AntPathRequestMatcher(path)
    }

    override fun matches(request: HttpServletRequest): Boolean {
        if (precondition(request)){
            return delagate.matches(request)
        }
        return false
    }

    protected abstract fun precondition(request: HttpServletRequest): Boolean
}
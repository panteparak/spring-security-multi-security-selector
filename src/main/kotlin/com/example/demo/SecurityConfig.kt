package com.example.demo

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.http.HttpServletRequest


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http.csrf().disable()

        http.requestMatcher(object : AntPathRequestMatcherWrapper("/**") {
            override fun precondition(request: HttpServletRequest): Boolean {
                val allow = !request.getHeader("Authorization").startsWith("Bearer", true)
                println("non auth bearer $allow")
                return allow
            }
        }).authorizeRequests().anyRequest().authenticated()
    }
}



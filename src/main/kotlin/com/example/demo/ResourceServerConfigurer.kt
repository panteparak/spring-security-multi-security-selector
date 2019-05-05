package com.example.demo

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import javax.servlet.http.HttpServletRequest

@EnableResourceServer
@Configuration
class ResourceServerConfigurer : ResourceServerConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        http.requestMatcher(object : AntPathRequestMatcherWrapper("/*"){
            override fun precondition(request: HttpServletRequest): Boolean {
                val allow = request.getHeader("Authorization").startsWith("Bearer ")
//                println("Auth Bearer $allow")
                return allow
            }
        }).authorizeRequests().anyRequest().authenticated()
    }
}
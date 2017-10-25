package com.jcminarro.authexample.config.security

import com.jcminarro.authexample.config.HeadersName
import com.jcminarro.authexample.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class AuthenticationAccessTokenFilter(val userRepository: UserRepository) : UsernamePasswordAuthenticationFilter() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        request as HttpServletRequest
        request.getHeader(HeadersName.ACCESS_TOKEN)?.let { accessToken ->
            userRepository.getUserIdByAccessToken(accessToken)?.let { userId ->
                SecurityContextHolder.getContext().authentication =
                        UsernamePasswordAuthenticationToken(userId, null, listOf(GrantedAuthority { "AuthExample" }))
            }
        }
        chain.doFilter(request, response)
    }
}
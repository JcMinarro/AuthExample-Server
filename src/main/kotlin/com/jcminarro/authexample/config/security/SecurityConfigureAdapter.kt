package com.jcminarro.authexample.config.security

import com.jcminarro.authexample.config.EndpointPath
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfigureAdapter(
        val entryPointUnauthorizedHandler: EntryPointUnauthorizedHandler) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(entryPointUnauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers(EndpointPath.LOGIN).permitAll()
                    .anyRequest().authenticated()
    }
}
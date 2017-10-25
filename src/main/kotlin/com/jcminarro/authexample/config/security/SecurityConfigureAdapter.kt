package com.jcminarro.authexample.config.security

import com.jcminarro.authexample.config.EndpointPath
import com.jcminarro.authexample.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfigureAdapter(
        val userRepository: UserRepository,
        val entryPointUnauthorizedHandler: EntryPointUnauthorizedHandler) : WebSecurityConfigurerAdapter() {

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean() = super.authenticationManagerBean()

    @Bean
    @Throws(Exception::class)
    fun authenticationAccessTokenFilterBean() =
            AuthenticationAccessTokenFilter(userRepository).apply { setAuthenticationManager(authenticationManagerBean()) }

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
                    .antMatchers(
                            EndpointPath.LOGIN,
                            EndpointPath.REFRESH).permitAll()
                    .anyRequest().authenticated()
        http
                .addFilterBefore(authenticationAccessTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)
    }
}
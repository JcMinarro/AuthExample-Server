package com.jcminarro.authexample.login

import com.google.gson.annotations.SerializedName
import com.jcminarro.authexample.config.EndpointPath
import com.jcminarro.authexample.model.OAuth
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(val loginService: LoginService){

    @RequestMapping(value = EndpointPath.LOGIN,
            method = arrayOf(RequestMethod.POST), 
            consumes = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun login(@RequestBody loginBody: LoginBoby): OAuth =
            loginService.login(loginBody.username, loginBody.password)
}

data class LoginBoby(
        @SerializedName("username") val username: String,
        @SerializedName("password") val password: String)
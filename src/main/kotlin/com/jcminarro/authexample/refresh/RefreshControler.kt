package com.jcminarro.authexample.refresh

import com.google.gson.annotations.SerializedName
import com.jcminarro.authexample.config.EndpointPath
import com.jcminarro.authexample.model.OAuth
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshControler(val refreshService: RefreshService){

    @RequestMapping(value = EndpointPath.REFRESH,
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun login(@RequestBody refreshBody: RefreshBoby): OAuth =
            refreshService.refreshToken(refreshBody.refreshToken)
}

data class RefreshBoby(
        @SerializedName("refreshToken") val refreshToken: String)
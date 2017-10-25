package com.jcminarro.authexample.quote

import com.jcminarro.authexample.config.EndpointPath
import com.jcminarro.authexample.model.Quote
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class QuoteControler(val quoteService: QuoteService){

    @RequestMapping(value = EndpointPath.QUOTE,
            method = arrayOf(RequestMethod.GET),
            consumes = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun getQuote(): Quote =
            quoteService.getQuote()
}
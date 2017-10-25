package com.jcminarro.authexample.quote

import com.jcminarro.authexample.repository.QuoteRepository
import org.springframework.stereotype.Component

@Component
class QuoteService(val quoteRepository: QuoteRepository){

    fun getQuote() =
            quoteRepository.getRandomQuote()
}
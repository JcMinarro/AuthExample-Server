package com.jcminarro.authexample.repository

import com.jcminarro.authexample.model.Quote
import org.springframework.stereotype.Component
import java.util.*

@Component
class QuoteRepository{
    private val quotes: List<Quote> =
            listOf(
                    Quote("Linus Torvalds", "If you think your users are idiots only idiots will use it."),
                    Quote("Linus Torvalds", "Talk is cheap. Show me the code."),
                    Quote("Albert Einstein", "Two things are infinite, the universe and human stupidity, and I am not yet completely sure about the universe."),
                    Quote("Glenn Seaborg", "Atomic power will make electricity too cheap to meter."),
                    Quote("Adam Steinberg", "I don't know if they would do that."),
                    Quote("Chuck Price", "I don't know that we're seeing eye to eye on value. I think we're close."),
                    Quote("Thomas M. Disch", "Gender and the complications it gives rise to simply aren't relevant to the lives appliances lead."),
                    Quote("Alan Thompson", "Celtic is one of the world's biggest clubs and this agreement sets the seal on an amazing month for me, what with overcoming Barcelona, winning my first cap for England and now signing this extension")
            )

    fun getRandomQuote() =
            quotes[Random(System.currentTimeMillis()).nextInt(quotes.size)]
}
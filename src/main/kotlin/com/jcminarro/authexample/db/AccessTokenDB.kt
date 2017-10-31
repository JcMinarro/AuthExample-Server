package com.jcminarro.authexample.db

import org.springframework.stereotype.Component

@Component
class AccessTokenDB: InMemoryCacheMap<Int>(30 * 1000)

package com.jcminarro.authexample.db

import org.springframework.stereotype.Component

@Component
class RefreshTokenDB: MutableMap<String, Int> by map
private val map = hashMapOf<String, Int>()
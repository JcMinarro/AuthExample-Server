package com.jcminarro.authexample.db

import com.jcminarro.authexample.model.User
import org.springframework.stereotype.Component

@Component
class UserDB: MutableMap<Int, User> by map
private val map: MutableMap<Int, User> = hashMapOf()
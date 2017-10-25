package com.jcminarro.authexample.login

import com.jcminarro.authexample.model.OAuth
import com.jcminarro.authexample.model.User
import com.jcminarro.authexample.repository.UserRepository
import com.jcminarro.authexample.sha256
import org.springframework.stereotype.Service


@Service
class LoginService(val userRepository: UserRepository) {

    fun login(username: String, password: String): OAuth =
            userRepository.getUser(generateUserId(username))
                    ?.let {
                        if (isSamePassword(it, password)) userRepository.refreshOAuth(it)
                        else throw Exception()
                    } ?: userRepository.refreshOAuth(createNewUser(username, password))

    private fun isSamePassword(user: User, password: String): Boolean =
            user.password == encodePassword(password)

    private fun createNewUser(username: String, password: String): User =
            User(generateUserId(username), username, encodePassword(password))

    private fun encodePassword(password: String) = sha256(password)

    private fun generateUserId(username: String) = username.hashCode()
}
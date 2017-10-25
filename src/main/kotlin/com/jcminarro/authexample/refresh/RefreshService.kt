package com.jcminarro.authexample.refresh

import com.jcminarro.authexample.config.error.InvalidRefreshTokenException
import com.jcminarro.authexample.model.OAuth
import com.jcminarro.authexample.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RefreshService(val userRepository: UserRepository){

    fun refreshToken(refreshToken: String): OAuth =
            userRepository
                    .getUserByRefreshToken(refreshToken)
                    ?.let{ userRepository.refreshOAuth(it) }
    ?: throw InvalidRefreshTokenException()
}
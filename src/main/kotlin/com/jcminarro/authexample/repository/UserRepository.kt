package com.jcminarro.authexample.repository

import com.jcminarro.authexample.db.AccessTokenDB
import com.jcminarro.authexample.db.RefreshTokenDB
import com.jcminarro.authexample.db.UserDB
import com.jcminarro.authexample.generateRandomString
import com.jcminarro.authexample.model.OAuth
import com.jcminarro.authexample.model.User
import com.jcminarro.authexample.sha256
import org.springframework.stereotype.Component

@Component
class UserRepository(val accessTokenDB: AccessTokenDB, val refreshTokenDB: RefreshTokenDB, val userDB: UserDB) {

    fun getUser(id: Int) = userDB[id]

    fun refreshOAuth(user: User) =
            user.let {
                userDB.put(it.id, it)
                generateOAuth(it).apply {
                    refreshTokenDB.put(refreshToken, it.id)
                    accessTokenDB.put(accessToken, it.id)
                }
            }

    private fun generateAccessToken(user: User): String =
            "${generateRandomString(5)}${sha256(user.username)}${generateRandomString(5)}"

    private fun generateRefreshToken(user: User): String =
            "${generateRandomString(6)}${sha256(user.username)}${generateRandomString(6)}"

    private fun generateOAuth(user: User): OAuth =
            OAuth(generateAccessToken(user),
                    generateRefreshToken(user))
}
package com.jcminarro.authexample.model

import com.google.gson.annotations.SerializedName

data class User(val id: Int, val username: String, val password: String)
data class OAuth(@SerializedName("accessToken") val accessToken: String,
                 @SerializedName("refreshToken") val refreshToken: String)
data class Quote(@SerializedName("author") val author: String,
                 @SerializedName("message") val message: String)
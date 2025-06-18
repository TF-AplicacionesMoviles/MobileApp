package com.example.dentifymobile.iam.data.model

import com.example.dentifymobile.iam.domain.model.Login
import com.example.dentifymobile.iam.domain.model.Register
import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("username")
    val username: String
){
    fun toDomain(): Login{
        return Login(accessToken, refreshToken)
    }
}
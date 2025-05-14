package com.example.dentifymobile.iam.data.model

import com.example.dentifymobile.iam.domain.model.Login
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String

) {
    fun toDomain(): Login {
        return Login(accessToken, refreshToken)
    }
}
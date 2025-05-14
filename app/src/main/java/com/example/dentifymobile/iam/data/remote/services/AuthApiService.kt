package com.example.dentifymobile.iam.data.remote.services

import com.example.dentifymobile.iam.data.model.LoginResponse
import com.example.dentifymobile.iam.data.remote.dto.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}

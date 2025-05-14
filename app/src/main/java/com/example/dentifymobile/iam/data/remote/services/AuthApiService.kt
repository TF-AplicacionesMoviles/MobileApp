package com.example.dentifymobile.iam.data.remote.services

import com.example.dentifymobile.iam.data.model.LoginResponse
import com.example.dentifymobile.iam.data.model.RegisterResponse
import com.example.dentifymobile.iam.data.remote.dto.LoginRequest
import com.example.dentifymobile.iam.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse
}

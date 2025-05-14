package com.example.dentifymobile.iam.data.repository

import com.example.dentifymobile.iam.data.remote.dto.LoginRequest
import com.example.dentifymobile.iam.data.remote.services.AuthApiService
import com.example.dentifymobile.iam.domain.model.Login
import com.example.dentifymobile.iam.domain.repository.AuthRepository

class AuthRepositoryImpl (
    private val apiService: AuthApiService
): AuthRepository {
    override suspend fun login(username: String, password: String): Login {
        val dto = LoginRequest(username, password)
        val response = apiService.login(dto)
        return response.toDomain()
    }
}
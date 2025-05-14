package com.example.dentifymobile.iam.domain.repository

import com.example.dentifymobile.iam.domain.model.Login

interface AuthRepository {
    suspend fun login(username: String, password: String): Login
}
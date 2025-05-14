package com.example.dentifymobile.iam.domain.repository

import com.example.dentifymobile.iam.domain.model.Login
import com.example.dentifymobile.iam.domain.model.Register

interface AuthRepository {
    suspend fun login(username: String, password: String): Login
    suspend fun register(register: Register): Login
}
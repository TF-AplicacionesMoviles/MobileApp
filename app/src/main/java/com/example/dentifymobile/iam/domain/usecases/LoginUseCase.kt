package com.example.dentifymobile.iam.domain.usecases

import com.example.dentifymobile.iam.domain.model.Login
import com.example.dentifymobile.iam.domain.repository.AuthRepository

class LoginUseCase (
    private val repository: AuthRepository
){
    suspend operator fun invoke(username: String, password: String): Login {
        return repository.login(username, password)
    }
}
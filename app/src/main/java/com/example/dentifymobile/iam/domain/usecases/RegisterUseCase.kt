package com.example.dentifymobile.iam.domain.usecases

import com.example.dentifymobile.iam.domain.model.Login
import com.example.dentifymobile.iam.domain.model.Register
import com.example.dentifymobile.iam.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(register: Register ): Login {
        return repository.register(register)
    }
}
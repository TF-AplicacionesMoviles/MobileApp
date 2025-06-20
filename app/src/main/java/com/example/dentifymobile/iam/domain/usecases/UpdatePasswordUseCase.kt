package com.example.dentifymobile.iam.domain.usecases

import com.example.dentifymobile.iam.data.remote.dto.UpdatePasswordRequest
import com.example.dentifymobile.iam.domain.repository.ProfileRepository

class UpdatePasswordUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(request: UpdatePasswordRequest) {
        repository.updatePassword(request)
    }
}
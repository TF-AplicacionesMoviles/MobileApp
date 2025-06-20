package com.example.dentifymobile.iam.domain.usecases

import com.example.dentifymobile.iam.data.model.UserInfoResponse
import com.example.dentifymobile.iam.domain.repository.ProfileRepository

class GetUserInfoUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(): UserInfoResponse {
        return repository.getUserInfo()
    }
}


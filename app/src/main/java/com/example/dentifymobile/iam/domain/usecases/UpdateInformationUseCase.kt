package com.example.dentifymobile.iam.domain.usecases

import com.example.dentifymobile.iam.data.remote.dto.UpdateInformationRequest
import com.example.dentifymobile.iam.domain.repository.ProfileRepository

class UpdateInformationUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(request: UpdateInformationRequest) {
        repository.updateInformation(request)
    }
}
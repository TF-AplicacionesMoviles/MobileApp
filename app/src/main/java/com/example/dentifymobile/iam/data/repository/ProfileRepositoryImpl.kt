package com.example.dentifymobile.iam.data.repository

import com.example.dentifymobile.iam.data.model.UserInfoResponse
import com.example.dentifymobile.iam.data.remote.dto.UpdateInformationRequest
import com.example.dentifymobile.iam.data.remote.dto.UpdatePasswordRequest
import com.example.dentifymobile.iam.data.remote.services.ProfileService
import com.example.dentifymobile.iam.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(private val profileService: ProfileService): ProfileRepository {
    override suspend fun getUserInfo(): UserInfoResponse = withContext(Dispatchers.IO) {
        val response = profileService.getUserInfo()
        if (response.isSuccessful) {
            response.body() ?: throw Exception("Empty body")
        } else {
            throw Exception("Error ${response.code()}: ${response.message()}")
        }
    }

    override suspend fun updatePassword(updatePasswordRequest: UpdatePasswordRequest) = withContext(Dispatchers.IO) {
        val response = profileService.updatePassword(updatePasswordRequest)
        if (!response.isSuccessful) {
            throw Exception("Failed to update password: ${response.code()} - ${response.message()}")
        }
    }

    override suspend fun updateInformation(updateInformationRequest: UpdateInformationRequest) = withContext(
        Dispatchers.IO) {
        val response = profileService.updateInformation(updateInformationRequest)
        if (!response.isSuccessful) {
            throw Exception("Failed to update profile info: ${response.code()} - ${response.message()}")
        }
    }
}
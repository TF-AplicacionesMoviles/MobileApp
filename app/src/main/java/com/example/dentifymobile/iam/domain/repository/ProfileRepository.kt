package com.example.dentifymobile.iam.domain.repository

import com.example.dentifymobile.iam.data.model.UserInfoResponse
import com.example.dentifymobile.iam.data.remote.dto.UpdateInformationRequest
import com.example.dentifymobile.iam.data.remote.dto.UpdatePasswordRequest

interface ProfileRepository {
    suspend fun getUserInfo(): UserInfoResponse
    suspend fun updatePassword(updatePasswordRequest: UpdatePasswordRequest)
    suspend fun updateInformation(updateInformationRequest: UpdateInformationRequest)
}
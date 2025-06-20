package com.example.dentifymobile.iam.data.remote.services

import com.example.dentifymobile.iam.data.model.UserInfoResponse
import com.example.dentifymobile.iam.data.remote.dto.UpdateInformationRequest
import com.example.dentifymobile.iam.data.remote.dto.UpdatePasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileService {
    @GET("v1/profile")
    suspend fun getUserInfo(): Response<UserInfoResponse>
    @PUT("v1/profile/update-password")
    suspend fun updatePassword(@Body request: UpdatePasswordRequest): Response<Void>

    @PUT("v1/profile/update-information")
    suspend fun updateInformation(@Body request: UpdateInformationRequest): Response<Void>

}
package com.example.dentifymobile.patientattention.data.remote.services

import com.example.dentifymobile.patientattention.data.model.MedicalHistoryResponse
import com.example.dentifymobile.patientattention.data.remote.dto.MedicalHistoryRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MedicalHistoryService {

    @GET("v1/patients/{id}/medical-histories")
    suspend fun getAllMedicalHistories(@Path("id") id: Long): Response<List<MedicalHistoryResponse>>

    @POST("v1/patients/{id}/medical-histories")
    suspend fun addMedicalHistories(@Path("id") id: Long, @Body medicalHistoryRequest: MedicalHistoryRequest): Response<MedicalHistoryResponse>

}
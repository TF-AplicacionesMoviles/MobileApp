package com.example.dentifymobile.patientattention.data.remote.services

import com.example.dentifymobile.patientattention.data.model.PatientResponse
import com.example.dentifymobile.patientattention.data.remote.dto.PatientRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PatientService {

    @GET("v1/patients")
    suspend fun getAllPatients(): Response<List<PatientResponse>>

    @POST("v1/patients")
    suspend fun addPatient(@Body patient: PatientRequest): Response<Map<String, Long>>
//    Response<PatientResponse>

    @DELETE("v1/patients/{id}")
    suspend fun deletePatient(@Path("id") id: Long): Response<Unit>

    @PUT("v1/patients/{id}")
    suspend fun updatePatient(@Path("id") id: Long, @Body patient: PatientRequest): Response<PatientResponse>
}
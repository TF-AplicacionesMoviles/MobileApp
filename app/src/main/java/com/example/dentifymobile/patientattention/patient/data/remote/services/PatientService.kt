package com.example.dentifymobile.patientattention.patient.data.remote.services

import com.example.dentifymobile.patientattention.patient.data.model.PatientResponse
import com.example.dentifymobile.patientattention.patient.data.remote.dto.PatientRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientService {

    @GET("v1/patients")
    suspend fun getAllPatients(): Response<List<PatientResponse>>


    @POST("v1/patients")
    suspend fun addPatient(@Body patient: PatientRequest): Response<PatientResponse>

    @DELETE("v1/patients/{id}")
    suspend fun deletePatient(@Path("id") id: Long): Response<Unit>
}
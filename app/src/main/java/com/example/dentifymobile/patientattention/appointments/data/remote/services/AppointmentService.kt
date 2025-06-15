package com.example.dentifymobile.patientattention.appointments.data.remote.services

import com.example.dentifymobile.patientattention.appointments.data.model.AppointmentResponse
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.AddAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.UpdateAppointmentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AppointmentService {
    @GET("v1/appointments")
    suspend fun getAllAppointments(): Response<List<AppointmentResponse>>
    @DELETE("v1/appointments/{id}")
    suspend fun deleteAppointment(@Path("id") id: Long): Response<Unit>
    @POST("v1/appointments")
    suspend fun addAppointment(@Body appointment: AddAppointmentRequest): Response<Unit>
    @PUT("v1/appointments/{id}")
    suspend fun updateAppointment(@Path("id") id: Long, @Body appointment: UpdateAppointmentRequest ): Response<AppointmentResponse>

}
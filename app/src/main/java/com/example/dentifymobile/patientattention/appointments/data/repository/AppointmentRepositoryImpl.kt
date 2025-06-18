package com.example.dentifymobile.patientattention.appointments.data.repository

import android.util.Log
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.AddAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.UpdateAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.data.remote.services.AppointmentService
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppointmentRepositoryImpl (
    private val appointmentService: AppointmentService
): AppointmentRepository
{
    override suspend fun getAllAppointments(): List<Appointment> = withContext(Dispatchers.IO){
       val response = appointmentService.getAllAppointments()
       if (response.isSuccessful){
           return@withContext response.body()
               ?.map {
                   it.toDomain()
               }
               ?: emptyList()
       } else{
           Log.e("AppointmentRepositoryImpl", "Error fetching appointment")
           return@withContext emptyList()
       }
    }

    override suspend fun deleteAppointment(id: Long) = withContext(Dispatchers.IO) {
        val response = appointmentService.deleteAppointment(id)
        if (!response.isSuccessful){
            Log.e("AppointmentRepositoryImpl", "Error deleting appointment: ${response.code()} - ${response.message()}")

        }
    }

    override suspend fun addAppointment(addAppointmentRequest: AddAppointmentRequest) = withContext(Dispatchers.IO) {
        val response = appointmentService.addAppointment(
            addAppointmentRequest
        )

        if (!response.isSuccessful){
            Log.e("AppointmentRepositoryImpl", "Error adding appointment: ${response.code()} - ${response.message()}")
        }
    }

    override suspend fun updateAppointment(
        id: Long,
        appointment: UpdateAppointmentRequest
    ) = withContext(Dispatchers.IO) {
        val response = appointmentService.updateAppointment(id,
            UpdateAppointmentRequest(
                appointmentDate = appointment.appointmentDate,
                reason = appointment.reason,
                duration = appointment.duration
            ))

        if(!response.isSuccessful) {
            Log.e("AppointmentRepositoryImpl", "Error updating appointment: ${response.code()} - ${response.message()}")
        }
    }
}
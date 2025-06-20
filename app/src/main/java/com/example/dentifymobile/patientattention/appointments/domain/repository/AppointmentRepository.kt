package com.example.dentifymobile.patientattention.appointments.domain.repository

import com.example.dentifymobile.patientattention.appointments.data.remote.dto.AddAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.UpdateAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment

interface AppointmentRepository {
    suspend fun getAllAppointments(): List<Appointment>
    suspend fun deleteAppointment(id: Long)
    suspend fun addAppointment(addAppointmentRequest: AddAppointmentRequest)
    suspend fun updateAppointment(id: Long, appointment: UpdateAppointmentRequest)
    suspend fun getAppointmentById(id: Long): Appointment?
}
package com.example.dentifymobile.patientattention.appointments.data.model

import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import java.time.LocalTime


data class AppointmentResponse (
    val id: Long,
    val patientName: String,
    val dni: String,
    val appointmentDate: String,
    val reason: String,
    val completed: Boolean,
    val duration: String,
    val createdAt: String
){

    fun toDomain(): Appointment {
        return Appointment(id, patientName, dni, appointmentDate, reason, completed, duration, createdAt)
    }
}
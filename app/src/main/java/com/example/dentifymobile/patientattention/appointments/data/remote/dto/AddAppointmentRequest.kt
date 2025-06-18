package com.example.dentifymobile.patientattention.appointments.data.remote.dto

data class AddAppointmentRequest(
    val appointmentDate: String,
    val reason: String,
    val duration: String,
    val patientId: Long
)

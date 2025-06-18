package com.example.dentifymobile.patientattention.appointments.data.remote.dto

data class UpdateAppointmentRequest(
    val appointmentDate: String,
    val reason: String,
    val duration: String
)

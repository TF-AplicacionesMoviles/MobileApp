package com.example.dentifymobile.patientattention.appointments.domain.model

data class Appointment(
    val id: Long,
    val patientName: String,
    val dni: String,
    val appointmentDate: String,
    val reason: String,
    val completed: Boolean,
    val duration: String,
    val createdAt: String
)
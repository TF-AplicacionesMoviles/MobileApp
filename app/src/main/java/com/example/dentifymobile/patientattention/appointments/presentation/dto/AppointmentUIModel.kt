package com.example.dentifymobile.patientattention.appointments.presentation.dto

data class AppointmentUIModel(
    val id: Long,
    val patientName: String,
    val dni: String,
    val appointmentDate: String,
    val appointmentHour: String,
    val reason: String,
    val completed: Boolean,
    val durationFormatted: String, // HH:MM
    val createdAt: String
)
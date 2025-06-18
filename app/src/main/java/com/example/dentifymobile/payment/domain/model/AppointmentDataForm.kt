package com.example.dentifymobile.payment.domain.model

data class AppointmentDataForm(
    val id: Long,
    val patientName: String,
    val reason: String,
    val completed: Boolean
)

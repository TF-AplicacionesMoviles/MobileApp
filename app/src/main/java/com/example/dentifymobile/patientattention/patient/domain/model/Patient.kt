package com.example.dentifymobile.patientattention.patient.domain.model

data class Patient(
    val id: Long,
    val dni: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val homeAddress: String,
    val birthday: String
)

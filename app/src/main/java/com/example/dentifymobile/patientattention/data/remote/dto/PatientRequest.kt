package com.example.dentifymobile.patientattention.data.remote.dto

data class PatientRequest(
    val dni: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val homeAddress: String,
    val birthday: String
)

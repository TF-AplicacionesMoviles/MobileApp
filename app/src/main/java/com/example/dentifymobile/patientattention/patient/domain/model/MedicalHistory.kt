package com.example.dentifymobile.patientattention.patient.domain.model

data class MedicalHistory(
    val id: Long,
    val description: String,
    val record: String,
    val diagnosis: String,
    val medication: String,
    val date: String,
)

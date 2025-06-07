package com.example.dentifymobile.patientattention.medicalhistory.data.remote.dto

data class MedicalHistoryRequest(
    val description: String,
    val record: String,
    val diagnosis: String,
    val medication: String
)

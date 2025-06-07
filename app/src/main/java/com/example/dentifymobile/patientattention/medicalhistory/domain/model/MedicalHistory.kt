package com.example.dentifymobile.patientattention.medicalhistory.domain.model

data class MedicalHistory(
    val description: String,
    val record: String,
    val diagnosis: String,
    val medication: String,
    val date: String,
    var isSelected: Boolean = false
)

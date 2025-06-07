package com.example.dentifymobile.patientattention.medicalhistory.data.model

import com.example.dentifymobile.patientattention.medicalhistory.domain.model.MedicalHistory

data class MedicalHistoryResponse(
    val description: String?,
    val record: String?,
    val diagnosis: String?,
    val medication: String?,
    val createdAt: String?
) {
    fun toMedical(): MedicalHistory {
        return MedicalHistory(
            description = description ?: "",
            record = record ?: "",
            diagnosis = diagnosis ?: "",
            medication = medication ?: "",
            date = createdAt ?: ""
        )
    }
}

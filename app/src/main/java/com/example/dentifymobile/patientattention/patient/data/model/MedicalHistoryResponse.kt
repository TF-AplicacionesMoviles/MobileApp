package com.example.dentifymobile.patientattention.patient.data.model


import com.example.dentifymobile.patientattention.patient.domain.model.MedicalHistory

data class MedicalHistoryResponse(
    val id: Long?,
    val description: String?,
    val record: String?,
    val diagnosis: String?,
    val medication: String?,
    val createdAt: String?
) {
    fun toDomain(): MedicalHistory {
        return MedicalHistory(
            id = id ?: 0L,
            description = description ?: "",
            record = record ?: "",
            diagnosis = diagnosis ?: "",
            medication = medication ?: "",
            date = createdAt ?: ""
        )
    }
}

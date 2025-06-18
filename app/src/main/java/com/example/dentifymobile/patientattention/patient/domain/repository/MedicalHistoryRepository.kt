package com.example.dentifymobile.patientattention.patient.domain.repository

import com.example.dentifymobile.patientattention.patient.domain.model.MedicalHistory

interface MedicalHistoryRepository {
    suspend fun getAllMedicalHistories(id: Long): List<MedicalHistory>
    suspend fun addMedicalHistory(id: Long, medicalHistory: MedicalHistory)
}
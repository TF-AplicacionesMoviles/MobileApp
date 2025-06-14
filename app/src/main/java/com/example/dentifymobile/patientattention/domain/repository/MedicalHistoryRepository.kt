package com.example.dentifymobile.patientattention.domain.repository

import com.example.dentifymobile.patientattention.domain.model.MedicalHistory

interface MedicalHistoryRepository {
    suspend fun getAllMedicalHistories(id: Long): List<MedicalHistory>
    suspend fun addMedicalHistory(id: Long, medicalHistory: MedicalHistory)
}
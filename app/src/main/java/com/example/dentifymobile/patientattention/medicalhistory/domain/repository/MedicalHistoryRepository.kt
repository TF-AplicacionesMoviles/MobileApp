package com.example.dentifymobile.patientattention.medicalhistory.domain.repository

import com.example.dentifymobile.patientattention.medicalhistory.domain.model.MedicalHistory

interface MedicalHistoryRepository {
    suspend fun getAllMedicalHistories(id: Long): List<MedicalHistory>
    suspend fun addMedicalHistory(id: Long, medicalHistory: MedicalHistory)
}
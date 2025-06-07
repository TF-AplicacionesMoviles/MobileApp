package com.example.dentifymobile.patientattention.medicalhistory.domain.usecases

import com.example.dentifymobile.patientattention.medicalhistory.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.medicalhistory.domain.repository.MedicalHistoryRepository

class AddMedicalHistoryUseCase(
    private val medicalHistoryRepository: MedicalHistoryRepository
) {
    suspend operator fun invoke(id: Long, medicalHistory: MedicalHistory) {
        return medicalHistoryRepository.addMedicalHistory(id, medicalHistory)
    }
}
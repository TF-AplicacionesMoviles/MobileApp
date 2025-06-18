package com.example.dentifymobile.patientattention.patient.domain.usecases


import com.example.dentifymobile.patientattention.patient.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.patient.domain.repository.MedicalHistoryRepository

class AddMedicalHistoryUseCase(
    private val medicalHistoryRepository: MedicalHistoryRepository
) {
    suspend operator fun invoke(id: Long, medicalHistory: MedicalHistory) {
        return medicalHistoryRepository.addMedicalHistory(id, medicalHistory)
    }
}
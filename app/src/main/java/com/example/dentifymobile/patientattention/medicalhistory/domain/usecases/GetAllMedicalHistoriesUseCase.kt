package com.example.dentifymobile.patientattention.medicalhistory.domain.usecases

import com.example.dentifymobile.patientattention.medicalhistory.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.medicalhistory.domain.repository.MedicalHistoryRepository

class GetAllMedicalHistoriesUseCase(private val medicalHistoryRepository: MedicalHistoryRepository) {
    suspend operator fun invoke(id: Long): List<MedicalHistory> {
        return medicalHistoryRepository.getAllMedicalHistories(id)
    }
}
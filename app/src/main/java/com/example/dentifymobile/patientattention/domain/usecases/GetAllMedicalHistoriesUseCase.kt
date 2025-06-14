package com.example.dentifymobile.patientattention.domain.usecases

import com.example.dentifymobile.patientattention.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.domain.repository.MedicalHistoryRepository

class GetAllMedicalHistoriesUseCase(private val medicalHistoryRepository: MedicalHistoryRepository) {
    suspend operator fun invoke(id: Long): List<MedicalHistory> {
        return medicalHistoryRepository.getAllMedicalHistories(id)
    }
}
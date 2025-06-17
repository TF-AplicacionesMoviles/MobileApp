package com.example.dentifymobile.patientattention.patient.domain.usecases

import com.example.dentifymobile.patientattention.appointments.domain.model.PatientDataForm
import com.example.dentifymobile.patientattention.patient.domain.repository.PatientRepository

class GetAllPatientFormInfoUseCase(private val repository: PatientRepository) {
    suspend operator fun invoke(): List<PatientDataForm> {
        return repository.getAllPatients().map { it.toPatientDataForm() }
    }
}
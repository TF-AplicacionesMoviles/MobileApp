package com.example.dentifymobile.patientattention.patient.domain.usecases

import com.example.dentifymobile.patientattention.patient.domain.repository.PatientRepository

class DeletePatientUseCase(private val repository: PatientRepository) {

    suspend operator fun invoke(id: Long) {
        return repository.deletePatient(id)
    }

}
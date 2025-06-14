package com.example.dentifymobile.patientattention.domain.usecases

import com.example.dentifymobile.patientattention.domain.repository.PatientRepository

class DeletePatientUseCase(private val repository: PatientRepository) {

    suspend operator fun invoke(id: Long) {
        return repository.deletePatient(id)
    }

}
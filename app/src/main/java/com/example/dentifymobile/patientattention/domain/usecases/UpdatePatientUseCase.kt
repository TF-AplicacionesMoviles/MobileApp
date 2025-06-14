package com.example.dentifymobile.patientattention.domain.usecases

import com.example.dentifymobile.patientattention.domain.model.Patient
import com.example.dentifymobile.patientattention.domain.repository.PatientRepository

class UpdatePatientUseCase(
    private val repository: PatientRepository
) {
    suspend operator fun invoke(id:Long, patient: Patient) {
        return repository.updatePatient(id, patient)
    }
}
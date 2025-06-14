package com.example.dentifymobile.patientattention.domain.usecases

import com.example.dentifymobile.patientattention.domain.model.Patient
import com.example.dentifymobile.patientattention.domain.repository.PatientRepository

class GetAllPatientsUseCase(
    private val repository: PatientRepository
) {

    suspend operator fun invoke(): List<Patient> {
        return repository.getAllPatients()
    }

}
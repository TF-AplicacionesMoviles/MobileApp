package com.example.dentifymobile.patientattention.patient.domain.usecases

import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.domain.repository.PatientRepository

class AddPatientUseCase(private val repository: PatientRepository) {

    suspend operator fun invoke(patient: Patient) {
        return repository.addPatient(patient)
    }

}
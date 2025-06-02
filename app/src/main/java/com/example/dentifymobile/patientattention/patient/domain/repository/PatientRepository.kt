package com.example.dentifymobile.patientattention.patient.domain.repository

import com.example.dentifymobile.patientattention.patient.domain.model.Patient

interface PatientRepository {
    suspend fun getAllPatients(): List<Patient>
    suspend fun addPatient(patient: Patient)
    suspend fun deletePatient(id: Long)
    suspend fun updatePatient(id: Long, patient: Patient)
}
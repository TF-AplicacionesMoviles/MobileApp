package com.example.dentifymobile.patientattention.data.repository

import android.util.Log
import com.example.dentifymobile.patientattention.data.remote.dto.PatientRequest
import com.example.dentifymobile.patientattention.data.remote.services.PatientService
import com.example.dentifymobile.patientattention.domain.model.Patient
import com.example.dentifymobile.patientattention.domain.repository.PatientRepository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext

class PatientRepositoryImpl(
    private val patientService: PatientService
) : PatientRepository {


    companion object {
        private const val TAG = "PatientRepositoryImpl"
    }

    override suspend fun getAllPatients(): List<Patient> = withContext(Dispatchers.IO) {
        val response = patientService.getAllPatients()
        if (response.isSuccessful) {
            return@withContext response.body()
                ?.map {
                    it.toDomain()
                }
                ?: emptyList()
        } else {
            Log.e(TAG, "Error fetching patients: ${response.code()} - ${response.message()}")
            return@withContext emptyList()
        }
    }


    override suspend fun addPatient(patient: Patient) = withContext(Dispatchers.IO) {
        val response = patientService.addPatient(
            PatientRequest(
                dni = patient.dni,
                firstName = patient.firstName,
                lastName = patient.lastName,
                email = patient.email,
                homeAddress = patient.homeAddress,
                birthday = patient.birthday
            )
        )
        if (!response.isSuccessful) {
            Log.e(TAG, "Error adding patient: ${response.code()} - ${response.message()}")
        }
    }


    override suspend fun deletePatient(id: Long) = withContext(Dispatchers.IO) {
        val response = patientService.deletePatient(id)
        if (!response.isSuccessful) {
            Log.e(TAG, "Error deleting patient: ${response.code()} - ${response.message()}")
        }
    }

    override suspend fun updatePatient(id: Long, patient: Patient) = withContext(Dispatchers.IO) {
        val response = patientService.updatePatient(id,
            PatientRequest(
                dni = patient.dni,
                firstName = patient.firstName,
                lastName = patient.lastName,
                email = patient.email,
                homeAddress = patient.homeAddress,
                birthday = patient.birthday
            )
        )

        if(!response.isSuccessful) {
            Log.e(TAG, "Error updating patient: ${response.code()} - ${response.message()}")
        }
    }
}
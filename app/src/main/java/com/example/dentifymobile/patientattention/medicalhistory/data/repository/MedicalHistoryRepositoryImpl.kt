package com.example.dentifymobile.patientattention.medicalhistory.data.repository

import android.util.Log
import com.example.dentifymobile.patientattention.medicalhistory.data.remote.dto.MedicalHistoryRequest
import com.example.dentifymobile.patientattention.medicalhistory.data.remote.services.MedicalHistoryService
import com.example.dentifymobile.patientattention.medicalhistory.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.medicalhistory.domain.repository.MedicalHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicalHistoryRepositoryImpl(
    private val medicalHistoryService: MedicalHistoryService
) : MedicalHistoryRepository {
    companion object {
        private const val TAG = "MedicalHistoryRepositoryImpl"
    }

    override suspend fun getAllMedicalHistories(id: Long): List<MedicalHistory> = withContext(Dispatchers.IO) {
        val response = medicalHistoryService.getAllMedicalHistories(id)
        if (response.isSuccessful) {
            return@withContext response.body()
                ?.map {
                    it.toMedical()
                }
                ?: emptyList()
        } else {
            Log.e(TAG, "Error fetching medical histories: ${response.code()} - ${response.message()}")
            return@withContext emptyList()
        }
    }

    override suspend fun addMedicalHistory(id: Long, medicalHistory: MedicalHistory) = withContext(Dispatchers.IO) {
        val response = medicalHistoryService.addMedicalHistories(id,
            MedicalHistoryRequest(
                description = medicalHistory.description,
                record = medicalHistory.record,
                diagnosis = medicalHistory.diagnosis,
                medication = medicalHistory.medication
            )
        )
        if (!response.isSuccessful) {
            Log.e(TAG, "Error adding medical histories: ${response.code()} - ${response.message()}")
        }
    }
}
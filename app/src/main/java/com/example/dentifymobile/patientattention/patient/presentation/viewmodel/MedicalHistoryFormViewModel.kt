package com.example.dentifymobile.patientattention.patient.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.patient.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.domain.usecases.AddMedicalHistoryUseCase
import kotlinx.coroutines.launch

class MedicalHistoryFormViewModel(
    private val addMedicalHistoryUseCase: AddMedicalHistoryUseCase
): ViewModel() {

    var selectedPatient = mutableStateOf<Patient?>(null)

    fun addMedicalHistory(id: Long, medicalHistory: MedicalHistory) {
        viewModelScope.launch {
            addMedicalHistoryUseCase(id, medicalHistory)
        }
    }

    fun setSelectedPatient(patient: Patient) {
        selectedPatient.value = patient
    }

}
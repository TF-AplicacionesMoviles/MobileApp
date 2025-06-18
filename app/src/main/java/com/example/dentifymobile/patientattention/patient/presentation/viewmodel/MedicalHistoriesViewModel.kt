package com.example.dentifymobile.patientattention.patient.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.patient.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.domain.usecases.GetAllMedicalHistoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MedicalHistoriesViewModel(
    private val getAllMedicalHistoriesUseCase: GetAllMedicalHistoriesUseCase
): ViewModel() {
    private val _medicalHistories = MutableStateFlow<List<MedicalHistory>>(emptyList())
    val medicalHistories: StateFlow<List<MedicalHistory>> = _medicalHistories

    var selectedPatient = mutableStateOf<Patient?>(null)

    fun getAllMedical(id: Long) {
        viewModelScope.launch {
            _medicalHistories.value = getAllMedicalHistoriesUseCase(id)
        }
    }

    fun setSelectedPatient(patient: Patient) {
        selectedPatient.value = patient
    }

}
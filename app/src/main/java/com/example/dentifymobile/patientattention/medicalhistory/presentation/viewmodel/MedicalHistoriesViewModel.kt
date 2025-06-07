package com.example.dentifymobile.patientattention.medicalhistory.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.medicalhistory.domain.model.MedicalHistory
import com.example.dentifymobile.patientattention.medicalhistory.domain.usecases.GetAllMedicalHistoriesUseCase
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
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

    fun toggleMedicalSelected(index: Int, isSelected: Boolean) {
        _medicalHistories.value = _medicalHistories.value.mapIndexed { i, medical ->
            if (i == index) medical.copy(isSelected = isSelected) else medical
        }
    }
}
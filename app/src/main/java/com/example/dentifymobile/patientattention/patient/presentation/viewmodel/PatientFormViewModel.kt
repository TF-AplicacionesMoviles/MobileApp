package com.example.dentifymobile.patientattention.patient.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.domain.usecases.AddPatientUseCase
import com.example.dentifymobile.patientattention.patient.domain.usecases.UpdatePatientUseCase
import kotlinx.coroutines.launch

class PatientFormViewModel(
    private val addPatientUseCase: AddPatientUseCase,
    private val updatePatientUseCase: UpdatePatientUseCase
): ViewModel() {

    var selectedPatient = mutableStateOf<Patient?>(null)

    fun addPatient(patient: Patient) {
        viewModelScope.launch {
            addPatientUseCase(patient)
        }
    }

    fun updatePatient(id: Long, patient: Patient) {
        viewModelScope.launch {
            updatePatientUseCase(id, patient)
        }
    }

    fun setSelectedPatient(patient: Patient) {
        selectedPatient.value = patient
    }

    fun clearSelectedPatient() {
        selectedPatient.value = null
    }
}
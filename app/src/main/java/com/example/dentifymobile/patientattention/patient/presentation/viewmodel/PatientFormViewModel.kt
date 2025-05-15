package com.example.dentifymobile.patientattention.patient.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.domain.usecases.AddPatientUseCase
import kotlinx.coroutines.launch

class PatientFormViewModel(
    private val addPatientUseCase: AddPatientUseCase
): ViewModel() {

    fun addPatient(patient: Patient) {
        viewModelScope.launch {
            addPatientUseCase(patient)
        }
    }
}
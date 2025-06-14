package com.example.dentifymobile.patientattention.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.domain.model.Patient
import com.example.dentifymobile.patientattention.domain.usecases.DeletePatientUseCase
import com.example.dentifymobile.patientattention.domain.usecases.GetAllPatientsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatientsViewModel(
    private val getAllPatientsUseCase: GetAllPatientsUseCase,
    private val deletePatientUseCase: DeletePatientUseCase
): ViewModel() {

    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients: StateFlow<List<Patient>> = _patients

    fun getAllPatients() {
        viewModelScope.launch {
            _patients.value = getAllPatientsUseCase()
        }
    }

    fun deletePatient(id: Long) {
        viewModelScope.launch {
            deletePatientUseCase(id)
            getAllPatients()
        }
    }
}
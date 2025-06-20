package com.example.dentifymobile.patientattention.appointments.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.AddAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.data.remote.dto.UpdateAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.model.PatientDataForm
import com.example.dentifymobile.patientattention.appointments.domain.usecases.AddAppointmentUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.GetAppointmentByIdUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.UpdateAppointmentUseCase
import com.example.dentifymobile.patientattention.appointments.presentation.dto.AppointmentUIModel
import com.example.dentifymobile.patientattention.appointments.presentation.mapper.toUiModel
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import com.example.dentifymobile.patientattention.patient.domain.usecases.GetAllPatientFormInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppointmentFormViewModel (
    private val addAppointmentUseCase: AddAppointmentUseCase,
    private val updateAppointmentUseCase: UpdateAppointmentUseCase,
    private val getAllPatientFormInfoUseCase: GetAllPatientFormInfoUseCase,
    private val getAppointmentByIdUseCase: GetAppointmentByIdUseCase,
    private val patients: MutableState<List<PatientDataForm>> = mutableStateOf<List<PatientDataForm>>(emptyList())

): ViewModel() {


    private val _currentAppointment = MutableStateFlow<AppointmentUIModel?>(null)
    val currentAppointment: StateFlow<AppointmentUIModel?> = _currentAppointment

    fun loadAppointmentById(id: Long) {
        viewModelScope.launch {
            try {
                val domainModel = getAppointmentByIdUseCase(id)
                domainModel?.let {
                    _currentAppointment.value = it.toUiModel()
                } ?: run {
                    Log.e("AppointmentFormVM", "Appointment not found for ID: $id")
                }
            } catch (e: Exception) {
                Log.e("AppointmentFormVM", "Error loading appointment", e)
            }
        }
    }

    fun addAppointment(appointment: AddAppointmentRequest){
        viewModelScope.launch {
            addAppointmentUseCase(appointment)
        }
    }


    fun updateAppointment(id: Long, appointment: UpdateAppointmentRequest){
        viewModelScope.launch {
            updateAppointmentUseCase(id, appointment)
        }
    }

    fun loadPatients() {
        viewModelScope.launch {
            try {
                patients.value = getAllPatientFormInfoUseCase()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getPatients(): MutableState<List<PatientDataForm>> {
        return patients
    }


}

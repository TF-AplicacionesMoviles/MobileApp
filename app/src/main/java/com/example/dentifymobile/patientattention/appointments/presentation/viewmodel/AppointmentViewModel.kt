package com.example.dentifymobile.patientattention.appointments.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.usecases.DeleteAppointmentUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.GetAllAppointmentsUseCase
import com.example.dentifymobile.patientattention.appointments.presentation.dto.AppointmentUIModel
import com.example.dentifymobile.patientattention.appointments.presentation.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppointmentViewModel(
    private val getAllAppointmentsUseCase: GetAllAppointmentsUseCase,
    private val deleteAppointmentUseCase: DeleteAppointmentUseCase
): ViewModel() {
    private val _appointments = MutableStateFlow<List<AppointmentUIModel>>(emptyList())
    val appointments: StateFlow<List<AppointmentUIModel>> = _appointments

    fun getAllAppointments(){
        viewModelScope.launch {
            val domainAppointments = getAllAppointmentsUseCase()
            _appointments.value = domainAppointments.map {it.toUiModel()}
        }
    }

    fun deleteAppointment(id: Long){
        viewModelScope.launch {
            try {
                deleteAppointmentUseCase(id)
                getAllAppointments()
            } catch (e: Exception){
                Log.e("AppointmentViewModel", "Error deleting appointment", e)
            }

        }

    }

    fun getAppointmentById(id: Long): AppointmentUIModel? {
        return appointments.value.find { it.id == id }
    }

}
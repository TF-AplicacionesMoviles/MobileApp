package com.example.dentifymobile.patientattention.appointments.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.usecases.DeleteAppointmentUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.GetAllAppointmentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppointmentViewModel(
    private val getAllAppointmentsUseCase: GetAllAppointmentsUseCase,
    private val deleteAppointmentUseCase: DeleteAppointmentUseCase
): ViewModel() {
    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> = _appointments

    fun getAllAppointments(){
        viewModelScope.launch {
            _appointments.value = getAllAppointmentsUseCase()
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

    fun getAppointmentById(id: Long): Appointment? {
        return appointments.value.find { it.id == id }
    }

}
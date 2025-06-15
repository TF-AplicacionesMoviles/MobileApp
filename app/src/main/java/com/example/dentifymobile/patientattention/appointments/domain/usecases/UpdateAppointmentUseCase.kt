package com.example.dentifymobile.patientattention.appointments.domain.usecases

import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository

class UpdateAppointmentUseCase(private val repository: AppointmentRepository) {
    suspend operator fun invoke(id: Long, appointment: Appointment){
        return repository.updateAppointment(id, appointment)
    }
}
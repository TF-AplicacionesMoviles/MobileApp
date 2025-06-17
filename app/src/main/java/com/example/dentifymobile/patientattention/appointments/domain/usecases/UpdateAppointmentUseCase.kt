package com.example.dentifymobile.patientattention.appointments.domain.usecases

import com.example.dentifymobile.patientattention.appointments.data.remote.dto.UpdateAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository

class UpdateAppointmentUseCase(private val repository: AppointmentRepository) {
    suspend operator fun invoke(id: Long, appointment: UpdateAppointmentRequest){
        return repository.updateAppointment(id, appointment)
    }
}
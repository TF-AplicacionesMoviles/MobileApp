package com.example.dentifymobile.patientattention.appointments.domain.usecases

import com.example.dentifymobile.patientattention.appointments.data.remote.dto.AddAppointmentRequest
import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository

class AddAppointmentUseCase(private val repository: AppointmentRepository) {
    suspend operator fun invoke(appointment: AddAppointmentRequest){
        return repository.addAppointment(appointment)
    }
}
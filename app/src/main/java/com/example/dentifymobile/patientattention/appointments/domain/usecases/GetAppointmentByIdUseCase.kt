package com.example.dentifymobile.patientattention.appointments.domain.usecases

import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository

class GetAppointmentByIdUseCase (
    private val repository: AppointmentRepository
) {
    suspend operator fun invoke(id: Long): Appointment? {
        return repository.getAppointmentById(id)
    }
}

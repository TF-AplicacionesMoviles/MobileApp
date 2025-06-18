package com.example.dentifymobile.patientattention.appointments.domain.usecases

import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository
import com.example.dentifymobile.payment.domain.model.AppointmentDataForm

class GetAllAppointmentsFormInfoUseCase(private val repository: AppointmentRepository) {
    suspend operator fun invoke(): List<AppointmentDataForm>{
        return repository.getAllAppointments().map { it.toAppointmentDataForm() }

    }
}
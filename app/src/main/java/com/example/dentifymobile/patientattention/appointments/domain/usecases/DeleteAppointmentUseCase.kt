package com.example.dentifymobile.patientattention.appointments.domain.usecases

import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository

class DeleteAppointmentUseCase(private val repository: AppointmentRepository) {
    suspend operator fun invoke(id: Long){
        return repository.deleteAppointment(id)
    }
}
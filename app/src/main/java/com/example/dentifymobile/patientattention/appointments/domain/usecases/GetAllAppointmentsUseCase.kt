package com.example.dentifymobile.patientattention.appointments.domain.usecases

import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository

class GetAllAppointmentsUseCase(private val repository: AppointmentRepository)
{
    suspend operator fun invoke(): List<Appointment>{
        return repository.getAllAppointments()
    }
}
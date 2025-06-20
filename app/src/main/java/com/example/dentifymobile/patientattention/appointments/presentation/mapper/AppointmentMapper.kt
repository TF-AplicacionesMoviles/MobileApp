package com.example.dentifymobile.patientattention.appointments.presentation.mapper

import com.example.dentifymobile.patientattention.appointments.domain.model.Appointment
import com.example.dentifymobile.patientattention.appointments.presentation.dto.AppointmentUIModel

fun Appointment.toUiModel(): AppointmentUIModel {
    val (datePart, timePart) = appointmentDate.split("T").let {
        val date = it.getOrNull(0) ?: ""
        val time = it.getOrNull(1)?.take(5) ?: "" // Solo HH:MM
        date to time
    }

    return AppointmentUIModel(
        id = id,
        patientName = patientName,
        dni = dni,
        appointmentDate = datePart,
        appointmentHour = timePart,
        reason = reason,
        completed = completed,
        durationFormatted = duration.take(5), // Asumiendo "HH:MM:SS"
        createdAt = createdAt.take(10)
    )
}
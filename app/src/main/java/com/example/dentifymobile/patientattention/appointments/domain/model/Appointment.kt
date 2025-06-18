package com.example.dentifymobile.patientattention.appointments.domain.model

import com.example.dentifymobile.payment.domain.model.AppointmentDataForm

data class Appointment(
    val id: Long,
    val patientName: String,
    val dni: String,
    val appointmentDate: String,
    val reason: String,
    val completed: Boolean,
    val duration: String,
    val createdAt: String
){
    fun toAppointmentDataForm(): AppointmentDataForm{
        return AppointmentDataForm(id, patientName, reason, completed)
    }
}
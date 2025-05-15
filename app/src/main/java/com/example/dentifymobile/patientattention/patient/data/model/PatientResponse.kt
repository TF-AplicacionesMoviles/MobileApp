package com.example.dentifymobile.patientattention.patient.data.model

import com.example.dentifymobile.patientattention.patient.domain.model.Patient

data class PatientResponse(
    val id: Long,
    val dni: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val homeAddress: String,
    val birthday: String
) {
    fun toDomain(): Patient {
        return Patient(id, dni, firstName, lastName, email, homeAddress, birthday)
    }
}

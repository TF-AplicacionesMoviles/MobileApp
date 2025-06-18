package com.example.dentifymobile.patientattention.patient.domain.model

import com.example.dentifymobile.patientattention.appointments.domain.model.PatientDataForm

data class Patient(
    val id: Long,
    val dni: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val homeAddress: String,
    val birthday: String,
    val age: Int
){
    fun toPatientDataForm(): PatientDataForm {
        return PatientDataForm(id, firstName, lastName)
    }

}

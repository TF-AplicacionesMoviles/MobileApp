package com.example.dentifymobile.patientattention.patient.data.model

import com.example.dentifymobile.patientattention.appointments.domain.model.PatientDataForm
import com.example.dentifymobile.patientattention.patient.domain.model.Patient
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
        return Patient(id, dni, firstName, lastName, email, homeAddress, birthday, calculateAge(birthday))
    }

    private fun calculateAge(birthDateString: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val birthDate = sdf.parse(birthDateString)

        val birthCalendar = Calendar.getInstance()
        birthCalendar.time = birthDate!!

        val today = Calendar.getInstance()

        var age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
    }
}

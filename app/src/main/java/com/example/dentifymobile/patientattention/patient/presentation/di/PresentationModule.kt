package com.example.dentifymobile.patientattention.patient.presentation.di

import android.content.Context
import com.example.dentifymobile.patientattention.patient.data.di.DataModule
import com.example.dentifymobile.patientattention.patient.presentation.viewmodel.PatientFormViewModel
import com.example.dentifymobile.patientattention.patient.presentation.viewmodel.PatientsViewModel

object PresentationModule {

    fun getPatientsViewModel(context: Context): PatientsViewModel {
        return PatientsViewModel(DataModule.getAllPatientsUseCase(context),
            DataModule.deletePatientUseCase(context))
    }

    fun getPatientFormViewModel(context: Context): PatientFormViewModel {
        return PatientFormViewModel(DataModule.addPatientUseCase(context),
            DataModule.updatePatientUseCase(context))
    }
}
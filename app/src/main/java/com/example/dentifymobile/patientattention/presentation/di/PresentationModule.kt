package com.example.dentifymobile.patientattention.presentation.di

import android.content.Context
import com.example.dentifymobile.patientattention.presentation.viewmodel.MedicalHistoriesViewModel
import com.example.dentifymobile.patientattention.data.di.DataModule
import com.example.dentifymobile.patientattention.presentation.viewmodel.MedicalHistoryFormViewModel
import com.example.dentifymobile.patientattention.presentation.viewmodel.PatientFormViewModel
import com.example.dentifymobile.patientattention.presentation.viewmodel.PatientsViewModel

object PresentationModule {

    fun getPatientsViewModel(context: Context): PatientsViewModel {
        return PatientsViewModel(
            DataModule.getAllPatientsUseCase(context),
            DataModule.deletePatientUseCase(context))
    }

    fun getPatientFormViewModel(context: Context): PatientFormViewModel {
        return PatientFormViewModel(
            DataModule.addPatientUseCase(context),
            DataModule.updatePatientUseCase(context))
    }

    fun getMedicalHistoriesViewModel(context: Context): MedicalHistoriesViewModel {
        return MedicalHistoriesViewModel(DataModule.getAllMedicalHistoriesUseCase(context))
    }

    fun getMedicalHistoryFormViewModel(context: Context): MedicalHistoryFormViewModel {
        return MedicalHistoryFormViewModel(DataModule.addMedicalHistoryUseCase(context))
    }
}
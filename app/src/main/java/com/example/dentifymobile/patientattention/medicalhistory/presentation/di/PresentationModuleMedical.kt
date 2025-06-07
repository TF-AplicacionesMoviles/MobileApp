package com.example.dentifymobile.patientattention.medicalhistory.presentation.di

import android.content.Context
import com.example.dentifymobile.patientattention.medicalhistory.data.di.DataModule
import com.example.dentifymobile.patientattention.medicalhistory.presentation.viewmodel.MedicalHistoriesViewModel

object PresentationModuleMedical {
    fun getMedicalHistoriesViewModel(context: Context): MedicalHistoriesViewModel {
        return MedicalHistoriesViewModel(DataModule.getAllMedicalHistoriesUseCase(context))
    }
}
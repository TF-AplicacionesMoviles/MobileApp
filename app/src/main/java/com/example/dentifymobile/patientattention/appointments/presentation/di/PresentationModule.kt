package com.example.dentifymobile.patientattention.appointments.presentation.di

import android.content.Context
import com.example.dentifymobile.patientattention.appointments.data.di.DataModule
import com.example.dentifymobile.patientattention.appointments.presentation.viewmodel.AppointmentFormViewModel
import com.example.dentifymobile.patientattention.appointments.presentation.viewmodel.AppointmentViewModel

object PresentationModule {
    fun getAppointmentsViewModel(context: Context): AppointmentViewModel {
        return AppointmentViewModel(DataModule.getAllAppointmentsUseCase(context), DataModule.deleteAppointmentUseCase(context))
    }
    fun getAppointmentFormViewModel(context: Context): AppointmentFormViewModel {
        return AppointmentFormViewModel(DataModule.addAppointmentUseCase(context),
            DataModule.updateAppointmentUseCase(context),
            DataModule.getAllPatientFormInfoUseCase(context)
        )
    }
}
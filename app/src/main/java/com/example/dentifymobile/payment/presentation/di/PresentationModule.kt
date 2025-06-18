package com.example.dentifymobile.payment.presentation.di

import android.content.Context
import com.example.dentifymobile.payment.data.di.DataModule
import com.example.dentifymobile.payment.presentation.viewmodel.InvoiceFormViewModel
import com.example.dentifymobile.payment.presentation.viewmodel.InvoiceViewModel

object PresentationModule {
    fun getInvoicesViewModel(context: Context): InvoiceViewModel {
        return InvoiceViewModel(DataModule.getAllInvoicesUseCase(context))
    }

    fun getInvoiceFormViewModel(context: Context): InvoiceFormViewModel {
        return InvoiceFormViewModel(DataModule.addInvoiceUseCase(context), DataModule.getAllAppointmentsFormInfoUseCase(context))
    }

}
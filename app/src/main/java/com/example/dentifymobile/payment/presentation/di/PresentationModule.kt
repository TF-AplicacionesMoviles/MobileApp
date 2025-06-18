package com.example.dentifymobile.payment.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dentifymobile.payment.presentation.viewmodel.FactureViewModel
import com.example.dentifymobile.payment.presentation.viewmodel.PaymentViewModel
import com.example.dentifymobile.payment.data.repository.FactureRepositoryImpl
import com.example.dentifymobile.payment.data.repository.PaymentRepositoryImpl
import com.example.dentifymobile.payment.domain.usecase.AddPaymentUseCase
import com.example.dentifymobile.payment.domain.usecase.GetPaymentsUseCase

object PresentationModule {

    // Crear PaymentViewModel
    fun getPaymentViewModel(context: Context): PaymentViewModel {
        val paymentRepository = PaymentRepositoryImpl() // Asegúrate de tener este repositorio
        val getPaymentsUseCase = GetPaymentsUseCase(paymentRepository)
        val addPaymentUseCase = AddPaymentUseCase(paymentRepository)

        return PaymentViewModel(getPaymentsUseCase, addPaymentUseCase)
    }

    // Crear FactureViewModel
    fun getFactureViewModel(context: Context): FactureViewModel {
        val factureRepository = FactureRepositoryImpl() // Asegúrate de tener este repositorio
        return FactureViewModel(factureRepository)
    }
}

package com.example.dentifymobile.payment.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.patientattention.appointments.domain.usecases.GetAllAppointmentsFormInfoUseCase
import com.example.dentifymobile.payment.data.remote.dto.AddInvoiceRequest
import com.example.dentifymobile.payment.domain.model.AppointmentDataForm
import com.example.dentifymobile.payment.domain.model.Invoice
import com.example.dentifymobile.payment.domain.usecases.AddInvoiceUseCase
import kotlinx.coroutines.launch

class InvoiceFormViewModel (
    private val addInvoiceUseCase: AddInvoiceUseCase,
    private val getAllAppointmentsFormInfoUseCase: GetAllAppointmentsFormInfoUseCase,
    private val appointments: MutableState<List<AppointmentDataForm>> = mutableStateOf<List<AppointmentDataForm>>(emptyList())
): ViewModel() {

    fun addInvoice(invoice: AddInvoiceRequest){
        viewModelScope.launch {
            try {
                addInvoiceUseCase(invoice)
                println("Factura enviada correctamente.")
            } catch (e: Exception) {
                println("Error al enviar la factura: ${e.message}")
            }
        }
    }
    fun loadAppointments(){
        viewModelScope.launch {
            try {
                val allAppointments = getAllAppointmentsFormInfoUseCase()
                appointments.value = allAppointments.filter { !it.completed }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAppointments(): MutableState<List<AppointmentDataForm>>{
        return appointments
    }
}